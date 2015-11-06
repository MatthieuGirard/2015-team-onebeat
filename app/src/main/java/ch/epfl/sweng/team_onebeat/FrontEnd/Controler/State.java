package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

/**
 * Created by hugo on 27.10.2015.
 */

public class State {


    private int value = -1;
    private String stateName;
    private MachineState.WorkType stateType;

    public State( String name, int value, MachineState.WorkType workType){
        this.value = value;
        this.stateName = name;
        this.stateType = workType;
    }


    public static State provide(MachineState.WorkType board, MachineState.ConnectionState state ){
        return new State( state.name(), state.ordinal(), board );
    }

    public static State provide(MachineState.WorkType board, MachineState.ActivityState state ){
        return new State(state.name(), state.ordinal(), board );
    }



    public MachineState.WorkType type(){
        return stateType;
    }

    public int get() {
        return value;
    }



    @Override
    public boolean equals(Object obj ){
        if(!(obj instanceof State)){
            return false;
        }

        State that = (State)obj;
        return this.value == that.value && this.stateType == that.stateType;

    }


    @Override
    public int hashCode(){
        return 31*stateType.ordinal() + value;
    }

    @Override
    public String toString(){
        return stateName;
    }

}


