package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import java.util.Observable;
import java.util.Observer;

import ch.epfl.sweng.team_onebeat.Exceptions.DisplayNotImplementedException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.MainActivity;

/*
observe machine state and when a state is modified, execute related code, ex :
request stuff to the backend/spotify, set the view, modify state.
 */

public class ControlerMainActivity implements Observer {



    private MainActivity activity;


    public ControlerMainActivity(MainActivity activity)
    {
        this.activity = activity;
    }


    // machine state is updated
    @Override
    public void update(Observable observable, Object data) {


        if(data instanceof MachineState){

            MachineState ms = (MachineState) data;

            if(ms.getType() == StaticMachine.Type.CONNECTION){

                try {
                switch(  StaticMachine.ConnectionState.values()[ ms.getState().value() ]  ){
                    case OFF :
                            activity.display(StaticMachine.ConnectionState.OFF);
                        break;
                    case TRY_CONNECT:
                        activity.display(StaticMachine.ConnectionState.TRY_CONNECT);
                        break;
                    case CONNECTED:
                        break;

                }
                } catch (DisplayNotImplementedException e) {
                    e.printStackTrace();
                }


            }

        }


    }







}