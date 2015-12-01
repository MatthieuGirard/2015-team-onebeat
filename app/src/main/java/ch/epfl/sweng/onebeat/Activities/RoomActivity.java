package ch.epfl.sweng.onebeat.Activities;

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

import org.json.JSONException;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.Network.DataProviderObserver;
import ch.epfl.sweng.onebeat.Network.SpotifyDataProvider;
import ch.epfl.sweng.onebeat.Parsers.JSONParser;
import ch.epfl.sweng.onebeat.Exceptions.JSONParserException;
import ch.epfl.sweng.onebeat.R;
import ch.epfl.sweng.onebeat.RetrievedData.Song;
import ch.epfl.sweng.onebeat.Network.WebPageDownloader;

public class RoomActivity extends AppCompatActivity implements DataProviderObserver {
    private ListView listViewSongs;
    private EditText addNextSong;

    //TODO: change currentSongs to be an array of songs but only after making a SongListAdapter
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
        //TODO: Find a way to differentiate when we want to display the delete menu or a list
        //      of possible song choices
        else if (v.getId() == addNextSong.getId()) {
            for (int i = 0; i < tempSongs.size(); i++) {
                Song s = tempSongs.get(i);
                menu.add(tempSongs.hashCode(), i, i, s.title + " by " + s.artist);
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

        if (searchInput.length() <= 0) {
            return;
        } else {
            Button button = (Button) findViewById(R.id.search_song_button);
            button.setEnabled(false);
            button.setText("Searching...");

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {

                new SpotifyDataProvider(this).getListOfSongs(searchInput);

            }
            // TODO: Show Toast if no connection
        }
    }

    public void addSong(Song song) {
        currentSongs.add(song);
        adapter.notifyDataSetChanged();
    }

    public void removeSong(int index) {
        // We need to delete a song from the currentSongs list
        currentSongs.remove(index);
        // Now notify the adapter the list has changed and it should be updated
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDataReception(Object data) {

        Button button = (Button) findViewById(R.id.search_song_button);
        button.setEnabled(true);
        button.setText("Search");

        tempSongs = (List<Song>) data;
        openContextMenu(addNextSong);
    }

}