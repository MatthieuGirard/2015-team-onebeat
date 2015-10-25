package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.TimeExceededException;

/**
 * Created by hugo on 26.10.2015.
 */
public abstract class RetrieveBuildableData<T> {

    private float alreadyDownloaded = 0;
    boolean loaded = false;

    private final float TIME_REFRESH_WHEN_BLOCKING = 30;

    public void setLoad(float alreadyDownloaded){
        this.alreadyDownloaded = alreadyDownloaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isBuildable() throws BuildableException {
        if(!isLoaded()) throw new BuildableException();
        return true;
    }

    public abstract void loadData(T that);

    public abstract T build() throws BuildableException ;



    public T blockAndRetrieve(float threshold)
            throws BuildableException, TimeExceededException {

        float totalTime = 0;
        while(isBuildable()){
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
    return  build();
    }



    public String toString(){
        return "buildable data";
    }

}
