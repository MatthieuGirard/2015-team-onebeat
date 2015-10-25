package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;

/**
 * Created by hugo on 25.10.2015.
 */
public interface RetrieveBuildableData<T> {

    public void copy(T that);
    public boolean isLoaded();
    public boolean isBuildable();
    public T build() throws BuildableException;


}
