package ch.epfl.sweng.team_onebeat.MiddleEnd.Network;

import android.os.AsyncTask;

import ch.epfl.sweng.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.Exceptions.TimeExceededException;
import ch.epfl.sweng.team_onebeat.MiddleEnd.Parser.Parser;

/**
 * Created by hugo on 26.10.2015.
 *
 *  data in pending mode (currently coming from backend)
 *  serveurUrl + networkProvider + request => JSONMessage(the response)
 *  parser<T> : response => T
 *
 *  the class load in a backgorund thread the request and put
 *  isLoaded to true when the "T instance" as been retrieve
 */
public class PendingData<T>  extends AsyncTask<Message, Void, T> {


    private final float TIME_REFRESH_WHEN_BLOCKING = 30;


    private float alreadyDownloaded = 0;
    private boolean loaded = false;
    private T instance = null;


    public PendingData(String serveurUrl,
                       NetworkProvider networkProvider,
                       Request request,
                       Parser<T> parser
                       ){

        throw new NotImplementedException();

    }



    @Override
    protected T doInBackground(Message... params) {
        // request => JSON
        // JSON => T
        // TODO : add % already loaded
        // TODO : how to throw BuildableException when parser fail or data corrupted ?
        // TODO : if threshold > 0 and exceeded : stop and launch TimeExceededException
        // setLoaded to true
        throw new NotImplementedException();
    }



    public boolean isLoaded() {
        return loaded;
    }



    public T getInstance() throws BuildableException {
        if(instance == null) throw new BuildableException();
        return instance;
    }


    public T waitAndGet(float threshold) throws TimeExceededException {

        float totalTime = 0;
        while(!isLoaded()){
            if(totalTime > threshold){
                throw new TimeExceededException();
            }
            try {
                wait((long) TIME_REFRESH_WHEN_BLOCKING);
            } catch (InterruptedException e)         {
                assert false : "builder doesn't has to be interupted";
            }
            totalTime += TIME_REFRESH_WHEN_BLOCKING;
        }
    return  instance;
    }


    @Override
    public String toString(){
        return "download data";
    }

}
