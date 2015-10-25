package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import java.util.Observable;

/**
 * Created by hugo on 25.10.2015.
 *
 * encapsulate data to an initial state
 * when the "DownloadedData" go in  Loaded State
 * the data notify observers.
 */
public class DownloadedData extends Observable {

    public enum LoadingState {INITIAL_STATE, LOADED}
    private LoadingState loadingState = LoadingState.INITIAL_STATE;

    public LoadingState loadingState(){
        return loadingState;
    }

    public void setLoaded(){
        loadingState = LoadingState.LOADED;
        notifyObservers();
    }

}
