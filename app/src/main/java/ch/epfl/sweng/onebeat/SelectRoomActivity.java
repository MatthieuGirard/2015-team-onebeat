package ch.epfl.sweng.onebeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.epfl.sweng.onebeat.R;

public class SelectRoomActivity extends AppCompatActivity {
    public final static String ROOM_NAME_MESSAGE = "ch.epfl.sweng.onebeat.MESSAGE";
    private ListView listViewRooms;

    private ArrayList roomsArray;
    private ArrayAdapter<String> adapter; //TODO: Change to type room

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewRooms = (ListView) findViewById(R.id.roomListView);

        //TODO: Pull from server list of rooms that belong to the user who just logged in
        // roomsArray = getRooms(user); Hopefully just Strings? Otherwise we need a RoomListAdapter class
        roomsArray = new ArrayList();
        roomsArray.add("Are & Bae");
        roomsArray.add("Jazz");
        roomsArray.add("Rap");

        adapter = new ArrayAdapter<String>(this, R.layout.room_item_list_view, roomsArray);
        listViewRooms.setAdapter(adapter);

        listViewRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("KEINFO", "Position Selected = " + Integer.toString(position));
                //onRoomSelect(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EditText roomSearch = (EditText) findViewById(R.id.searchRoomTextView);
        roomSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SelectRoomActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /*
     * Once a user selects a room by clicking an item on the listViewRoom, this function should
     * be called. It will then take the room name, and start a new RoomActivity.
     */
    public void onRoomSelect(int position) {
        // TODO: How to make listViewRooms call this on click
        // TODO: make this class the hierarchical parent of RoomActivity (edit in RoomActivity) //
        //       that will allow a "back" button to work

        Intent intent = new Intent(this, RoomActivity.class);
        //TODO: get position of the Room in the listViewRooms (call this item)
        //TODO: String roomName = item.getText().toString();
        String roomName = "Blank Room";

        intent.putExtra(ROOM_NAME_MESSAGE, roomName);
        startActivity(intent);

        // TODO: Edit RoomActivity to get the intent and set the room name
    }

}
