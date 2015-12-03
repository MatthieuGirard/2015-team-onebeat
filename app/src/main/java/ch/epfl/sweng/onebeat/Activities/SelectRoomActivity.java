package ch.epfl.sweng.onebeat.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.Exceptions.NotDefinedUserInfosException;
import ch.epfl.sweng.onebeat.R;
import ch.epfl.sweng.onebeat.RetrievedData.Room;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;

public class SelectRoomActivity extends AppCompatActivity {
    public final static String ROOM_NAME_MESSAGE = "ch.epfl.sweng.onebeat.ROOM_NAME_MESSAGE";

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

        // This next bit of code allows the "live" filtering feature to work
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:
                RoomCreatorDialogFragment dialog = new RoomCreatorDialogFragment();
                dialog.show(getSupportFragmentManager(), "Room Creator");
            }
        });
    }

    public void setListOfRooms(List<Room> roomsList) {

    }

    public void onNewRoomMessage(JSONObject result) {

    }


    @SuppressLint("ValidFragment")
    private class RoomCreatorDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SelectRoomActivity.this);
            // Get the layout inflater
            final LayoutInflater inflater = SelectRoomActivity.this.getLayoutInflater();

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            final View v = inflater.inflate(R.layout.dialog_create_room, null);
            builder.setView(v)
                // Add action buttons
                .setPositiveButton(R.string.partyOn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        EditText roomNameField = (EditText) v.findViewById(R.id.roomName);
                        EditText roomPasswordField = (EditText) v.findViewById(R.id.roomPassword);

                        String room = roomNameField.getText().toString().trim();

                        JSONObject jsonToSend = new JSONObject();
                        try {
                            jsonToSend.put("creator", SpotifyUser.getInstance().getPseudo()); // TODO
                            jsonToSend.put("name", room);
                            jsonToSend.put("password", roomPasswordField.getText().toString());
                        } catch (JSONException | NotDefinedUserInfosException e) {
                            //TODO: Figure out what to actually do in case of error
                            e.printStackTrace();
                        }
                        //excutePost("http://onebeat.pythonanywhere.com/createRoom", jsonToSend.toString());

                        Intent intent = new Intent(RoomCreatorDialogFragment.this.getActivity(),
                                RoomActivity.class);
                        intent.putExtra(ROOM_NAME_MESSAGE, room);
                        startActivity(intent);
                    }
                })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            RoomCreatorDialogFragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }
}
