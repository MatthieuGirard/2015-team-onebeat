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
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.PlaylistManagerData;
import ch.epfl.sweng.team_onebeat.R;

public class PlaylistManagerActivity extends AppCompatActivity {

    /*
    TODO : make the display methods :
            modify the state in StaticMachine.PlaylistManagerState to add one state
            by type of displayed view : (like naming your current display)
            add the code to display the view in the display method.
     */


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



    public void display(StaticMachine.PlaylistManagerState state)  {

        switch( state ){

            case DISPLAYING: // <= may be a name of a a view
                // may be the display of the playlist subscribed (use myPlaylists)
                // and make a fake instance on onCreate to test the view

                break;

            default: throw new NotImplementedException();


        }
    }


}
