package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import java.util.Observable;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;

/**
 * Created by hugo on 24.10.2015.
 *
 * use case :
 * encapsulate a User coming from the backend
 *
 * how :
 * with a backendTask
 *
 */
public final class UserData {

    private String pseudo;

    public UserData( String pseudo ){
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return pseudo;
    }


    public static class Builder extends RetrieveBuildableData<UserData>{


        @Override
        public void loadData(UserData that) {
            // TODO
        }

        @Override
        public UserData build() throws BuildableException {
            if(!isBuildable()) throw new BuildableException();
            // TODO
            return null;
        }

        @Override
        public String toString(){
            return "UserData";
        }
    }




}
