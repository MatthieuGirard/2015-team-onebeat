package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.TimeExceededException;

/**
 * Created by hugo on 26.10.2015.
 *
 *  it's a simply builder that's become buildable
 *  when load(Data t) has already parametrized the instance.
 *
 *
 */
public class DownloadData<T> {

    private float alreadyDownloaded = 0;
    boolean loaded = false;
    private final float TIME_REFRESH_WHEN_BLOCKING = 30;
    private T instance = null;



    public void setLoad(float alreadyDownloaded){
        this.alreadyDownloaded = alreadyDownloaded;
    }

    public void loadData(T that){
        this.instance = instance;
    }




    public boolean isLoaded() {
        return loaded;
    }

    public T get() throws BuildableException {
        return instance;
    }


    public T waitAndGet(float threshold)
            throws BuildableException, TimeExceededException {

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
