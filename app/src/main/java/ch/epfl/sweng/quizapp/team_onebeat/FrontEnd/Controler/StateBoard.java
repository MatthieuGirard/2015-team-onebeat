package ch.epfl.sweng.quizapp.team_onebeat.FrontEnd.Controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Created by hugo on 26.10.2015.
 *
 * StateBoard contains many different board (CONNECTION, DISPLAY [,..])
 * and keep track about the current state on each board. Each board has
 * only one instance.
 *
 * Observer can watch a board to obtain a callback when his current state change.
 *
 * Writer is required when a state is set on a board to record
 * history of write/to_current_state on a board.
 *
 *
 */


public class StateBoard {



    public enum BoardType {CONNECTION, DISPLAY}
    public enum Writer {CONTROLER, UNIT_TEST}

    private static Map<BoardType, State> boards = new HashMap<>();

    private static  List<String> historyTracker = new ArrayList<>();

    private static Map<BoardType,Set<Observer>> observersFor = new HashMap<>();



    public static void setState(State state, Writer writer ){

        historyTracker.add(writer + " setting " + state + " on " + state.board());
        boards.put(state.board(), state);
        for( Observer obs : observersFor.get(state.board())) {
            obs.notify();
        }

    }



    public static String trackerHistory( int last ){

        String acc = "";

        for( int i = Math.min(0, historyTracker.size() - last); i < historyTracker.size(); i++ ){
            acc =  historyTracker.get(i) + "\n" + acc;
        }

        return acc;
    }


    public static void subscribe( BoardType board, Observer obs){

        observersFor.get(board).add(obs);

    }











}
