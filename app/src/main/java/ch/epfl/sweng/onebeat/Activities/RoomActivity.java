package ch.epfl.sweng.onebeat.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.Network.DataProvider;
import ch.epfl.sweng.onebeat.Network.DataProviderObserver;
import ch.epfl.sweng.onebeat.Network.SpotifyDataProvider;
import ch.epfl.sweng.onebeat.R;
import ch.epfl.sweng.onebeat.RetrievedData.Song;

public class RoomActivity extends AppCompatActivity implements DataProviderObserver {
    private ListView listViewSongs;
    private EditText addNextSong;
    private ImageView prevPlayerButton;

    private ArrayList<Song> currentSongs;
    private ArrayAdapter<Song> adapter;

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
        setTitle(intent.getStringExtra(SelectRoomActivity.ROOM_NAME_MESSAGE));

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
        AsyncMsg msg = new AsyncMsg(song, REQUEST.ADD);
        new AsyncUpdateSongOnline().execute(msg);
    }

    //TODO: Before removing a song, update the currentSong list from the database
    public void removeSong(int index) {
        // We need to delete a song from the currentSongs list
        Song songToRemove = currentSongs.get(index);
        // TODO: Now that we know which song we want to remove, update the list from the database
        // then check to see if the song is still in the list at which point we delete it and post
        // the changes

        AsyncMsg msg = new AsyncMsg(songToRemove, REQUEST.REMOVE);
        new AsyncUpdateSongOnline().execute(msg);
    }

    @Override
    public void onDataReception(Object data, DataProvider.RequestTypes requestTypes) {
        Button button = (Button) findViewById(R.id.search_song_button);
        button.setEnabled(true);
        button.setText("Search");

        tempSongs = (List<Song>) data;
        openContextMenu(addNextSong);
    }

    public enum REQUEST {
        ADD, REMOVE
    }
    private class AsyncMsg {
        private Song song;
        private REQUEST message;

        public AsyncMsg(Song song, REQUEST message) {
            this.message = message;
            this.song = song;
        }
        public Song getSong() {
            return this.song;
        }
        public REQUEST getMessage() {
            return this.message;
        }
    }

    //TODO: Choose an appropriate input type whether it be a JSONObject or Song
    private class AsyncUpdateSongOnline extends AsyncTask<AsyncMsg, Void, String> {
        @Override
        protected String doInBackground(AsyncMsg... params) {
            AsyncMsg msg = params[0];
            switch (msg.getMessage()) {
                case ADD:
                    //TODO: Communicate with server to add this song to the room

                    currentSongs.add(msg.getSong());
                    break;
                case REMOVE:
                    //TODO: Communicate with server to try and remove this song

                    currentSongs.remove(msg.getSong());
                    break;
            }

            //TODO: After talking to the server, we either add or remove a song from currentSongs
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            adapter.notifyDataSetChanged();
        }
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
}