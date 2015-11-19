package ch.epfl.sweng.team_onebeat.FrontEnd.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.State;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.PlaylistManagerData;
import ch.epfl.sweng.team_onebeat.R;

public class PlaylistManagerActivity extends AppCompatActivity {


    private List<PlaylistManagerData> myPlaylists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private void setPlaylists(List<PlaylistManagerData> that){
        myPlaylists = new ArrayList<>(that);
    }


    // on an add
    // TODO : when a clic is done to add song need to popup search by is own
    /*
    and call
                    StaticMachine.get(StaticMachine.Type.PLAYLIST_MANAGER)
                        .setState(
                        State.Factory.provide(StaticMachine.PlaylistManagerState.NEW_PLAYLIST)
                        );

     */

    // on an remove
    // TODO : when a clic is done to remove a song
    /*
    and call
                    StaticMachine.get(StaticMachine.Type.PLAYLIST_MANAGER)
                        .setState(
                        State.Factory.provide(StaticMachine.PlaylistManagerState.UNSUBSCRIBE_PLAYLIST)
                        );

     */


    public void display(StaticMachine.PlaylistManagerState state)  {

        switch( state ){

            case DISPLAYING:
                // TODO the list of PlaylistData is set by the controler
                // just display the playlist name subscribed

                break;

            default: throw new NotImplementedException();


        }
    }


}
