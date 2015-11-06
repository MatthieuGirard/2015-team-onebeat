package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;

/**
 * Created by hugo on 26.10.2015.
 *
 * StateBoard contains different board (CONNECTION, DISPLAY [,..])
 * and keep track about the current state on each board.
 *
 * Observer can watch a board to obtain a callback when his current state change.
 *
 *
 *
 */


public class MachineState {

    // history by type
    private static Map<WorkType, LinkedList<State>> historyTracker = new HashMap<>();

    // available board
    public enum WorkType {CONNECTION, ACTIVITY}


    // CONNECTION
    public enum ConnectionState {INITIAL, SIGNATURE_AVAILABLE, SIGNATURE_EXISTING,
        TRY_CONNECT, TRY_SUBSCRIBE, CONNECTED
    }

    // ACTIVITY
    public enum ActivityState {MAIN_ACTIVITY}




    private WorkType type;

    private State currentState;


    public MachineState( WorkType type, State initialState ){

        this.type = type;
        this.currentState = initialState;

    }



    public void setState(State state){

        historyTracker.get(this.type).add(state);

        currentState = state;
        this.notifyAll();

    }



    @Override
    public boolean equals(Object that){

        if( that instanceof MachineState){
            // TODO
        }
        else {

        }
        new NotImplementedException();
        return false;
    }


    @Override
    public int hashCode(){
        // type + currenteState => int
        new NotImplementedException();
        return 0;
    }




    public static List<State> getHistory(WorkType type){
        if(historyTracker.keySet().contains(type)){
            return new ArrayList(historyTracker.get(type));
        }
        else {
            return new ArrayList();
        }


    }

    public static Map<WorkType,State> snapshot(){
        Map<WorkType, State> snap = new HashMap<>();

        for(WorkType workType : historyTracker.keySet()){
            snap.put(workType, historyTracker.get(workType).peek());
        }

        return snap;
    }





}
