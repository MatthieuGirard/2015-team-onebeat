package ch.epfl.sweng.onebeat.frontEnd.network;

import java.util.Observable;
import java.util.Observer;

import ch.epfl.sweng.onebeat.exception.BuildableException;

/**
 * Created by hugo on 26.10.2015.
 *
 *  data in pending mode (currently coming from backend)
 *  serveurUrl + networkProvider + request => JSONMessage(the response)
 *  parser<T> : response => T
 *
 *  the class load in a background thread the request and put
 *  the downloadState to LOADED when the "T instance" as been retrieve
 */
public class PendingData<T> extends Observable {


    private final float TIME_REFRESH_WHEN_BLOCKING = 30;

    public enum DownloadState {PENDING, LOADED, ERROR }

    private DownloadState downloadState = DownloadState.PENDING;

    private T data = null;


    public void setDownloadState(DownloadState state){
        downloadState = state;
        setChanged();
        notifyObservers();
    }

    public DownloadState downloadState() {
        return downloadState;
    }

    public boolean downloadStateIs(DownloadState thatState) {
        return downloadState == thatState;
    }


    public void setData(T that){
        this.data = that;
    }

    public T get() throws BuildableException {
        if(data == null) throw new BuildableException();
        return data;
    }


    @Override
    public void addObserver(Observer obs){
        super.addObserver(obs);
        if(downloadState != DownloadState.PENDING){
            setChanged();
            notifyObservers();
        }

    }

    @Override
    public String toString(){
        return "download data";
    }


    public PendingData<T> blockAtMost(int threshold){

        float totalTime = 0;

        while(downloadState() == DownloadState.PENDING && totalTime < threshold){

            try {
                Thread.sleep((long) TIME_REFRESH_WHEN_BLOCKING);
            } catch (InterruptedException e)         {
            }

            totalTime += TIME_REFRESH_WHEN_BLOCKING;

        }

        return this;
    }




}






