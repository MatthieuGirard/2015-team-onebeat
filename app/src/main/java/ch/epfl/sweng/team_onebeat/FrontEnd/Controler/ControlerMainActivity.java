package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import android.content.Intent;
import android.util.Log;

import java.net.CookieHandler;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import ch.epfl.sweng.team_onebeat.Exceptions.DisplayNotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.MainActivity;
import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.RoomActivity;

/*
observe machine state and when a state is modified, execute related code, ex :
request stuff to the backend/spotify, set the view, modify state.
 */

public class ControlerMainActivity implements Observer {



    private MainActivity activity;
    LinkedList<Thread> pool = new LinkedList<>();


    public ControlerMainActivity(MainActivity activity)
    {
        this.activity = activity;
    }


    private void emptyPool(){

        for(Thread d : pool){
            d.interrupt(); // TODO verify
        }

        pool.clear();

    }

    // machine state is updated
    @Override
    public void update(Observable observable, Object data) {

        // each call to update invalidate previous threads
        emptyPool();

        if(observable instanceof MachineState){
            Log.d("mainAct", "controler is updated because a machineState has changed his state");

            MachineState ms = (MachineState) observable;

            if(ms.getType() == StaticMachine.Type.CONNECTION){

                try {
                    switch(  StaticMachine.ConnectionState.values()[ ms.getState().value() ]  ){
                        case OFF :
                            activity.display(StaticMachine.ConnectionState.OFF);
                            break;
                        case TRY_CONNECT:

                            Log.d("mainAct", "controler see the machine state in : try_connect state : " +
                                    "apply the try_connect code to control view");

                            // put display in pending
                            activity.display(StaticMachine.ConnectionState.TRY_CONNECT);

                            // TODO : create request with the activity text field
                            // TODO : get a pendingData<SpotifyUser>
                            // TODO : make a thread :
                            // run until data ready
                            // put Machine state : connection to CONNECTED

                            // TODO : delete (wait 2s)
                            Thread t = new Thread(
                                    new Runnable(){

                                        @Override
                                        public void run() {

                                            try{
                                                Thread.sleep(1000);
                                                //put machine state to connected
                                                StaticMachine.get(StaticMachine.Type.CONNECTION)
                                                        .setState(State.Factory.provide(StaticMachine.ConnectionState.CONNECTED));

                                            }catch(InterruptedException e){}


                                        }
                                    }
                            );

                            t.start();


                            //-----------------------
                            break;
                        case CONNECTED:
                            Log.d("mainAct", "controler see the machine state in : connected state : " +
                                    "apply the connected code to control view");

                           Intent intent2 = new Intent(activity, RoomActivity.class);
                           activity.startActivity(intent2);
                            break;

                    }
                } catch (DisplayNotImplementedException e) {
                    e.printStackTrace();
                }



            }

        }


    }







}