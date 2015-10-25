package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

/**
 * Created by hugo on 25.10.2015.
 */
public interface Buildable<T> {

    public void copy(Buildable<T> that);
    public boolean isLoaded();
    public T build();


}
