package ch.epfl.sweng.onebeat.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.GeneralConstants;
import ch.epfl.sweng.onebeat.Network.BackendDataProvider;
import ch.epfl.sweng.onebeat.Network.DataProvider;
import ch.epfl.sweng.onebeat.Network.DataProviderObserver;
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

    //TODO try to play and pause a song with methods mPlayer.play(spotifytrack), mPlayer.pause()

    //TODO You can add a song with: new BackendDataProvider(this).addSong(Song, roomID)

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


        // Assign the room name by getting it from the intent which opened this room
        Intent intent = getIntent();
        new BackendDataProvider(this).getRoom(intent.getIntExtra(SelectRoomActivity.ROOM_ID_MESSAGE, 0));

        //TODO: Make currentSongs call a method which checks database if there was a list of songs
        currentSongs = new ArrayList<>();

        adapter = new SongListAdapter(this, currentSongs);
        listViewSongs.setAdapter(adapter);

        registerForContextMenu(listViewSongs);
        registerForContextMenu(addNextSong);

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

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

                new SpotifyDataProvider(this).getListOfSongs(searchInput);

            } else {
                // TODO: Show Toast if no connection, maybe also inside DownloadWebpageTask ?
                Context context = getApplicationContext();
                int dur = Toast.LENGTH_SHORT;
                CharSequence error_msg = "Connection error while searching for song. Please try again";

                Toast.makeText(context, error_msg, dur).show();

                // Enable the user to search again
                button.setEnabled(true);
                button.setText("Search");
            }
        }
    }

    //TODO: Before adding a song, update the currentSong list from the database

    public void addSong(Song song) {
        currentSongs.add(song);
        adapter.notifyDataSetChanged();
    }

    //TODO: Before removing a song, update the currentSong list from the database
    public void removeSong(int index) {
        // We need to delete a song from the currentSongs list
        Song songToRemove = currentSongs.remove(index);
        // TODO: Now that we know which song we want to remove, update the list from the database
        // then check to see if the song is still in the list at which point we delete it and post
        // the changes


        // Now notify the adapter the list has changed and it should be updated
        adapter.notifyDataSetChanged();
    }

    public void playerClick(View v) {
        ImageView currPlayerButton = (ImageView) v.findViewById(R.id.list_image);

        // Was there a song playing?
        if (prevPlayerButton != null && (boolean)prevPlayerButton.getTag()) {
            prevPlayerButton.setTag(false);
            prevPlayerButton.setImageResource(R.drawable.player_play);

            if (prevPlayerButton == currPlayerButton) {
                // Were we the ones who were playing? If so, we already stopped playing
                prevPlayerButton = null;
            } else {
                // Someone else was playing, now we play
                currPlayerButton.setTag(true);
                currPlayerButton.setImageResource(R.drawable.player_pause);
                prevPlayerButton = currPlayerButton;
            }
        } else {
            currPlayerButton.setTag(true);
            currPlayerButton.setImageResource(R.drawable.player_pause);
            prevPlayerButton = currPlayerButton;
        }
    }

    // when we have spotify suggestions after search request
    public void setListOfSongsFromSearch(List<Song> parsedResult) {
        tempSongs = parsedResult;
        openContextMenu(addNextSong);
        // TODO call 'new BackendDataProvider(this).addSong(Song aSong) when user chose the song to add.
    }

    // when room informations are retrieved from backend
    public void setRoomInformations(Room actualRoom) {
        // TODO What do to when I get the room information. Room's list of songs may be empty (new room).
        //TODO think about if this method is called after a refresh, not to interrupt song that is currently playing.
        //TODO well it shouldnt happen because of asynctask
    }

    // TODO to be called in method onCreate I suppose.
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
        // TODO refresh list?
    }
}