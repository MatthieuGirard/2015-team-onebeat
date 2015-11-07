package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import java.util.Observer;

/**
 * Created by hugo on 07.11.15.
 *
 * enum differents Type of machines,
 * enum differents related states
 * declare one static private instance of each machine
 *
 * provide a static method to update the machine of : Type, with a : State
 *
 */

public class StaticMachine {



    // available machines:
    public enum Type {CONNECTION, ACTIVITY, ROOM}





    // state for machine : CONNECTION
    public enum ConnectionState {OFF, TRY_CONNECT, CONNECTED}


    // state for machine : ACTIVITY
    public enum ActivityState {MAIN_ACTIVITY, ROOM_ACTIVITY}

    public enum RoomState { INITIAL, PENDING, PERSONAL, SEARCH }



    private static MachineState connectionMachine
            = new MachineState(
            Type.CONNECTION,
            State.Factory.provide(ConnectionState.OFF));


    private static MachineState activityMachine
            = new MachineState(
            Type.ACTIVITY,
            State.Factory.provide(ActivityState.MAIN_ACTIVITY));




    public static MachineState get(StaticMachine.Type type){
        switch (type) {
            case CONNECTION:
                return connectionMachine;
            case ACTIVITY:
                return activityMachine;
            default:
                throw new IllegalArgumentException();
        }
    }




}
