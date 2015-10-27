package ch.epfl.sweng.quizapp.team_onebeat.FrontEnd.Controler;

import java.util.Observable;

/**
 * Created by hugo on 27.10.2015.
 */
public class State {

    // related enum state
    public enum ConnectionState {INITIAL, SIGNATURE_AVAILABLE, SIGNATURE_EXISTING,
        TRY_CONNECT, TRY_SUBSCRIBE, CONNECTED
    }

    public enum DisplayState {MAIN_ACTIVITY}




    private int value = -1;
    private String stateName;
    private StateBoard.BoardType board;

    public State( String name, int value, StateBoard.BoardType board){
        this.value = value;
        this.stateName = name;
        this.board = board;
    }




    public static State provideState(StateBoard.BoardType board, ConnectionState stateFromEnum ){
        return new State(stateFromEnum.name(), stateFromEnum.ordinal(), board );
    }

    public static State provideState(StateBoard.BoardType board, DisplayState stateFromEnum ){
        return new State(stateFromEnum.name(), stateFromEnum.ordinal(), board );
    }



    public StateBoard.BoardType board(){
        return board;
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
        return this.value == that.value && this.board == that.board;

    }


    @Override
    public int hashCode(){
        return 31*board.ordinal() + value;
    }

    @Override
    public String toString(){
        return stateName;
    }

}


