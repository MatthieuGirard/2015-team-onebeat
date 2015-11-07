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
 * provide setter to update the currentState of the application.
 * can notify observers when the current state is modified
 *
 *
 */


public class MachineState extends Observable{


    // tracker
    private LinkedList<State> historyTracker = new LinkedList<>();




    private StaticMachine.Type type;

    private State currentState;


    public MachineState( StaticMachine.Type type, State initialState ){

        this.type = type;
        this.currentState = initialState;

    }



    public void setState(State state){

        historyTracker.push(state);

        currentState = state;
        this.setChanged();
        this.notifyObservers();

    }


    public StaticMachine.Type getType(){
        return type;
    }

    public State getState(){
        return currentState;
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




    public List<State> getHistory(){

        return new LinkedList<>(historyTracker);

    }







}
