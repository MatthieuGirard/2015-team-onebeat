package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

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
    public enum Type {CONNECTION, ACTIVITY, PLAYLIST_MANAGER}





    // states for CONNECTION
    public enum ConnectionState {OFF, TRY_CONNECT, CONNECTED}


    // states for ACTIVITY
    public enum ActivityState {MAIN_ACTIVITY, ROOM_ACTIVITY}

    // state for ROOM :
    public enum PlaylistManagerState { INITIAL,  SEARCH, NEW_PLAYLIST,
        UNSUBSCRIBE_PLAYLIST, GO_PLAYLIST, DISPLAYING }




    private static MachineState connectionMachine
            = new MachineState(
            Type.CONNECTION,
            State.Factory.provide(ConnectionState.OFF));


    private static MachineState activityMachine
            = new MachineState(
            Type.ACTIVITY,
            State.Factory.provide(ActivityState.MAIN_ACTIVITY));


    private static MachineState playListManagerMachine
            = new MachineState(
            Type.PLAYLIST_MANAGER,
            State.Factory.provide(PlaylistManagerState.INITIAL));




    public static MachineState get(StaticMachine.Type type){
        switch (type) {
            case CONNECTION:
                return connectionMachine;
            case ACTIVITY:
                return activityMachine;
            case PLAYLIST_MANAGER:
                return playListManagerMachine;
            default:
                throw new IllegalArgumentException();
        }
    }




}
