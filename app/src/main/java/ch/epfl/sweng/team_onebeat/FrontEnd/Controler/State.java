package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

/**
 * Created by hugo on 27.10.2015.
 * State are almost number,
 * Factory provide a way to instanciate a state from an enumeration
 *
 */

public class State {


    private int value = -1;
    private String stateName;


    public State( String name, int value ){
        this.value = value;
        this.stateName = name;
    }



    public static class Factory {

        public static State provide( StaticMachine.ConnectionState state ) {
            return new State(state.name(), state.ordinal());
        }


        public static State provide( StaticMachine.ActivityState state) {
            return new State(state.name(), state.ordinal());
        }

        public static State provide( StaticMachine.PlaylistManagerState state) {
            return new State(state.name(), state.ordinal());
        }
    }


    public int value() {
        return value;
    }



    @Override
    public boolean equals(Object obj ){ // state on different machine with same id are equals
        if(!(obj instanceof State)){
            return false;
        }

        State that = (State)obj;
        return this.value == that.value;

    }

    @Override
    public int hashCode(){
        return  value;
    }

    @Override
    public String toString(){
        return stateName;
    }

}


