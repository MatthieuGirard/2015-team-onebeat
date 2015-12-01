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

    private ArrayList roomsArray;
    private ArrayAdapter<String> adapter; //TODO: Change to type room

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listViewRooms = (ListView) findViewById(R.id.roomListView);

        //TODO: Pull from server list of rooms that belong to the user who just logged in
        // roomsArray = getRooms(user); Hopefully just Strings? Otherwise we need a RoomListAdapter class
        roomsArray = new ArrayList();
        roomsArray.add("Are & Bae");
        roomsArray.add("Jazz");
        roomsArray.add("Rap");

        adapter = new ArrayAdapter<>(this, R.layout.room_item_list_view, roomsArray);
        listViewRooms.setAdapter(adapter);

        listViewRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Change room type if it is not a string
                String room = adapter.getItem(position);

                Intent intent = new Intent(SelectRoomActivity.this, RoomActivity.class);

                intent.putExtra(ROOM_NAME_MESSAGE, room);
                startActivity(intent);
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

}
