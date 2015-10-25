package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import java.util.Observable;

/**
 * Created by hugo on 24.10.2015.
 *
 * encapsulate a user to an initial state
 * when the "DownloadedData" go in  Loaded State
 * the data notify observers.
 */
public final class UserData extends DownloadedData{

    private String pseudo;

    public UserData( String pseudo ){
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return pseudo;
    }


}
