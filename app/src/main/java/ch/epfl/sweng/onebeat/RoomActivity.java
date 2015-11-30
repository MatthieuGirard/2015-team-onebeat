package ch.epfl.sweng.onebeat;

import android.content.Context;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RoomActivity extends AppCompatActivity implements WebPageDownloader {
    private ListView listViewSongs;
    private EditText addNextSong;

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

        /*
        TODO: Make currentSongs call a method which checks if there was previously a list of songs
              that the user was playing.
        */
        currentSongs = new ArrayList<>();

        adapter = new SongListAdapter(this, currentSongs);
        listViewSongs.setAdapter(adapter);

        registerForContextMenu(listViewSongs);
        registerForContextMenu(addNextSong);

        EditText editText = (EditText) findViewById(R.id.addSongTextBox);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
            Log.v("KEINFO - ", "Con Menu inflated for song to delete");
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

    public void searchForSong(View view) {
        String searchInput = addNextSong.getText().toString().trim();

        if (searchInput.length() <= 0) {
            return;
        } else {
            Button button = (Button) findViewById(R.id.search_song_button);
            button.setEnabled(false);
            button.setText("Searching...");

            String stringUrl = "http://ws.spotify.com/search/1/track.json?q=" + searchInput.replace(" ", "%20");

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new DownloadWebpageTask(this).execute(stringUrl);
            }
            else {
                // TODO: Show Toast if no connection, maybe also inside DownloadWebpageTask ?
                Context context = getApplicationContext();
                int dur = Toast.LENGTH_SHORT;
                CharSequence error_msg = "Connection error while searching for song. Please try again";

                Toast.makeText(context, error_msg, dur).show();
            }
        }
    }

    @Override
    public void onPageDataRetrieved(String result) throws JSONException, JSONParserException {

        Button button = (Button) findViewById(R.id.search_song_button);
        button.setEnabled(true);
        button.setText("Search");

        List<Song> tracks = JSONParser.parseFromSearchAPI(result);
        tempSongs = tracks;
        openContextMenu(addNextSong);
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
}