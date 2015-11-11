package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import android.content.Intent;
import android.util.Log;

import org.json.JSONException;

import java.util.LinkedList;
import java.util.Observable;

import ch.epfl.sweng.team_onebeat.Exceptions.BackendCommunicationException;
import ch.epfl.sweng.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.MainActivity;
import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.PlaylistManagerActivity;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.MessageFactory;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.PendingData;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.BooleanParser;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.BooleanData;

/*
controler of the main activity : authentification
 */

public class ControlerMainActivity extends Controler {



    private MainActivity activity;
    LinkedList<Thread> pool = new LinkedList<>();


    public ControlerMainActivity(MainActivity activity)  {
        super();
        this.activity = activity;
    }


    private void emptyPool(){

        for(Thread d : pool){
            d.interrupt();
        }

        pool.clear();

    }



    private void tryConnectTask(){

        // make running in background

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                try {


                    // get a data in pending mode

                    PendingData<BooleanData> pData = null;

                    pData = pendingData(
                            BackendService.SPOTIFY_CONNECTION,
                            MessageFactory.Frontend.spotifyConnect("someone", 1234),
                            new BooleanParser()
                    );


                    // block until the data is ready || timer > 2000 ms
                    pData.blockAtMost(2000);

                    // suppose spotify return boolean about user connection
                    // TODO : spotifyUser
                    if(pData.downloadState() == PendingData.DownloadState.LOADED) {

                        BooleanData boolData = pData.get();

                        // if user connected
                        if (boolData.value() == true) {
                            StaticMachine.get(StaticMachine.Type.CONNECTION)
                                    .setState(State.Factory.provide(StaticMachine.ConnectionState.CONNECTED));
                        } else { // else go back to off connected state
                            StaticMachine.get(StaticMachine.Type.CONNECTION)
                                    .setState(State.Factory.provide(StaticMachine.ConnectionState.OFF));
                        }

                    }



            } catch (BackendCommunicationException | BuildableException | JSONException e1) {
                e1.printStackTrace();
            }




            }
        });

        pool.add(t);
        t.start();
    }



    // machine state is updated
    @Override
    public void update(Observable observable, Object data) {

        // each call to update invalidate previous threads
        emptyPool();

        if(observable instanceof MachineState){

            Log.d("#controler", "one machine state observed has changed : ");

            MachineState ms = (MachineState) observable;

            if(ms.getType() == StaticMachine.Type.CONNECTION){


                    switch(  StaticMachine.ConnectionState.values()[ ms.getState().value() ]  ){

                        case OFF : Log.d("#controler", "connectionState is off, apply commande");

                            activity.display(StaticMachine.ConnectionState.OFF);
                            break;


                        case TRY_CONNECT: Log.d("#controler", "connectionState is try_connect, apply commande");

                            // put display in pending
                            activity.display(StaticMachine.ConnectionState.TRY_CONNECT); //loadingView
                            tryConnectTask();
                            break;


                        case CONNECTED:  Log.d("#controler", "connectionState is connected, apply commande");

                            Intent intent2 = new Intent(activity, PlaylistManagerActivity.class);
                            activity.startActivity(intent2);
                            break;

                    }



            }

        }


    }








}