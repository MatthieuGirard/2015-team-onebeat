package ch.epfl.sweng.onebeat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.Exceptions.NotDefinedRoomInfosException;
import ch.epfl.sweng.onebeat.GeneralConstants;
import ch.epfl.sweng.onebeat.Network.BackendDataProvider;
import ch.epfl.sweng.onebeat.Network.SpotifyDataProvider;
import ch.epfl.sweng.onebeat.R;
import ch.epfl.sweng.onebeat.RetrievedData.Room;
import ch.epfl.sweng.onebeat.RetrievedData.Song;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;

public class RoomActivity extends AppCompatActivity implements PlayerNotificationCallback {

    private ListView listViewSongs;
    private EditText addNextSong;
    private ImageView prevPlayerButton;

    private ArrayList<Song> currentSongs;
    private ArrayAdapter<Song> adapter;

    private Player mPlayer;

    private SpotifyPlayer player;

    private Room actualRoom;

    /*
     * Between the RoomActivity and the user selecting a song that they want to add to the queue, I
     * will use tempSongs to keep a reference to the songs found on Spotify.
     */
    private List<Song> tempSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        listViewSongs = (ListView) findViewById(R.id.currentSongsList);
        addNextSong = (EditText) findViewById(R.id.addSongTextBox);

        //initPlayer();
        player = new SpotifyPlayer(this);

        currentSongs = new ArrayList<>();

        adapter = new SongListAdapter(this, currentSongs);
        listViewSongs.setAdapter(adapter);

        registerForContextMenu(listViewSongs);

        // Assign the room name by getting it from the intent which opened this room
        Intent intent = getIntent();
        // fetch room infos from backend
        new BackendDataProvider(this).getRoom(intent.getIntExtra(SelectRoomActivity.ROOM_ID_MESSAGE, 0));

        addNextSong.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    findViewById(R.id.search_song_button).performClick();
                    handled = true;
                }
                return handled;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        if (v.getId() == listViewSongs.getId()) {
            inflater.inflate(R.menu.delete_song_context_menu, menu);
        }

        else if (v.getId() == addNextSong.getId()) {
            for (int i = 0; i < tempSongs.size(); i++) {
                Song s = tempSongs.get(i);
                menu.add(tempSongs.hashCode(), i, i, s.getTitle() + " by " + s.getArtist());
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.delete_song_menu) {
            removeSong(info.position);
            return true;
        }
        /*
        else if:
            check to see if the item has an id. If so, it is a song that should be added from the
            list being displayed as a context menu.
         */
        else if (item.getGroupId() == tempSongs.hashCode()) {
            Log.d("KEINFO", item.getTitle().toString());
            addSong(tempSongs.get(item.getItemId()));
            tempSongs = null;
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    public void searchForSong(View view) throws MalformedURLException {
        String searchInput = addNextSong.getText().toString().trim();

        if (searchInput.length() > 0) {
            Button button = (Button) findViewById(R.id.search_song_button);
            button.setEnabled(false);
            button.setText("Searching...");

            new SpotifyDataProvider(this).getListOfSongs(searchInput);
        }
    }

    public void addSong(Song song) {

        addNextSong.setText("");
        new BackendDataProvider(this).addSong(song, actualRoom.getId());
    }

    public void removeSong(int index) {
        Song songToRemove = currentSongs.remove(index);
        new BackendDataProvider(this).removeSong(songToRemove, actualRoom.getId());
        adapter.notifyDataSetChanged();
    }

    public void playerClick(View v) {
        ImageView currPlayerButton = (ImageView) v.findViewById(R.id.list_image);
        int position = (int) currPlayerButton.getTag(R.string.playing_button_position);

        // Was there a song playing?
        if (prevPlayerButton != null && (boolean)prevPlayerButton.getTag(0)) {
            prevPlayerButton.setTag(R.string.playing_button_status, false);
            prevPlayerButton.setImageResource(R.drawable.player_play);
            player.pause();

            if (prevPlayerButton == currPlayerButton) {
                // Were we the ones who were playing? If so, we already stopped playing
                prevPlayerButton = null;
            } else {
                // Someone else was playing, now we play
                currPlayerButton.setTag(R.string.playing_button_status, true);
                currPlayerButton.setImageResource(R.drawable.player_pause);
                prevPlayerButton = currPlayerButton;
                player.play(currentSongs.get(position).getSpotifyRef());
            }
        } else {
            currPlayerButton.setTag(R.string.playing_button_status, true);
            currPlayerButton.setImageResource(R.drawable.player_pause);
            prevPlayerButton = currPlayerButton;
            player.play(currentSongs.get(position).getSpotifyRef());
        }
    }

    // when we have spotify suggestions after search request
    public void setListOfSongsFromSearch(List<Song> parsedResult) {

        // Enable the user to search again
        Button button = (Button) findViewById(R.id.search_song_button);
        button.setEnabled(true);
        button.setText("Search");

        tempSongs = parsedResult;
        openContextMenu(addNextSong);
    }

    // when room informations are retrieved from backend
    public void setRoomInformations(Room room) {
        actualRoom = room;
        try {
            setTitle(actualRoom.getName());
        } catch (NotDefinedRoomInfosException e) {
            //No Room Title Set
            setTitle("");
        }
        try {
            currentSongs.addAll(actualRoom.getSongs().keySet());
            adapter.notifyDataSetChanged();
            onPlaybackError(null, "We just added some of your previous songs");
        } catch (NotDefinedRoomInfosException e) {
            //There was no previous list of songs, carry on.
        }
        onPlaybackError(null, "You're all set to party!");
        registerForContextMenu(addNextSong);
    }

    public void initPlayer() {
        Config playerConfig = new Config(this, SpotifyUser.getInstance().getToken(), GeneralConstants.CLIENT_ID);

        mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                mPlayer = player;
                mPlayer.addPlayerNotificationCallback(RoomActivity.this);
            }
            @Override
            public void onError(Throwable throwable) {
                Log.e("RoomActivity", "Could not initialize player: " + throwable.getMessage());
            }
        });
    }

    public void refreshListOfSongs() {
        new BackendDataProvider(this).getRoom(actualRoom.getId());
        adapter.notifyDataSetChanged();
    }

    // method from Spotify Player. Probably here we're going to manage playing the next song when one is over.
    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
        // TODO
    }

    // let's show error on a Toast
    @Override
    public void onPlaybackError(ErrorType errorType, String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    // when server is done adding the song
    public void onSongAdded() {
        refreshListOfSongs();
    }
}