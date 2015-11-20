package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import android.content.Intent;
import android.util.Log;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.PlaylistManagerActivity;

/**
 * Created by hugo on 10.11.15.
 * controler of playlist manager activities (rooms on gui)
 */

public class ControlerPlaylistManagerActivity implements Observer {


    private PlaylistManagerActivity activity;
    LinkedList<Thread> pool = new LinkedList<>();


    public ControlerPlaylistManagerActivity(PlaylistManagerActivity activity)  {
        this.activity = activity;
    }


    private void emptyPool(){

        for(Thread d : pool){
            d.interrupt();
        }

        pool.clear();

    }




    // machine state is updated
    @Override
    public void update(Observable observable, Object data) {

        // each call to update invalidate previous threads
        emptyPool();


/*
initial : load subscribed playlist
displaying : playlist

 */
        //_-------------------------------
        if(observable instanceof MachineState){

            Log.d("#controler", "one machine state observed has changed : ");

            MachineState ms = (MachineState) observable;

            if(ms.getType() == StaticMachine.Type.CONNECTION) {
                switch (StaticMachine.PlaylistManagerState.values()[ms.getState().value()]) {

                    case INITIAL:
                        Log.d("#controler", "roomState is initial, apply commande ");

                        // load subscribed room
                        // setter on view
                        // displayInformation
                        StaticMachine.get(StaticMachine.Type.PLAYLIST_MANAGER).setState(
                                State.Factory.provide(StaticMachine.PlaylistManagerState.DISPLAYING)
                        );

                        break;

                    case DISPLAYING:

                        activity.display(StaticMachine.PlaylistManagerState.DISPLAYING);

                        break;

                    case SEARCH:
                        Log.d("#controler", "roomState is search, apply commande");


                        // getter of the search input

                        // ask backend

                        // activity has a setteur of playlistData (some data in pending mode in each playlist<= todo)

                        // setter in the view

                        StaticMachine.get(StaticMachine.Type.PLAYLIST_MANAGER).setState(
                                State.Factory.provide(StaticMachine.PlaylistManagerState.DISPLAYING));
                        break;

                    // view load his popup and sig :

                    case NEW_PLAYLIST:

                        // request to add a playlist to the backend
                        // get back the id of the new room
                        // move to playlistActivity

                        break;

                    case UNSUBSCRIBE_PLAYLIST:

                        //request to remove a playlist
                        break;

                    case GO_PLAYLIST:
                        Log.d("#controler", "roomState is goRoom, apply commande");
                        // move playlistState (Todo : refactor) to load some id room

                        // get the id of the back from the view
                        // go playlistActivity
                        Intent intent2 = new Intent(activity, PlaylistManagerActivity.class);
                        activity.startActivity(intent2);
                        break;


                }
            }

        }


    }





}
