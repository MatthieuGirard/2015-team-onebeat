package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import android.app.Activity;

import java.util.Map;

public class ControlerMainActivity {


    private Activity activity;

    private static ControlerMainActivity oneInstance = null;

    private Map<MachineState.WorkType,MachineState> machines;

    private ControlerMainActivity(Activity activity, Map<MachineState.WorkType, MachineState> machines)
    {

        this.activity = activity;
        this.machines = machines;

    }



    // activity
    public Activity getControledActivity(){
        return activity;
    }



    // activity can use the singleton property to modify the state
    private void setState(State state){
        machines.get(state.type()).setState(state);
    }



}