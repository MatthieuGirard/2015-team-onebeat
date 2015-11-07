package ch.epfl.sweng.team_onebeat.MiddleEnd.RetrieveData;

/**
 * Created by hugo on 24.10.2015.
 *
 * use case :
 * encapsulate a User coming from the backend
 *
 */
public final class UserData implements RetrieveData{

    private String pseudo;

    public UserData( String pseudo ){
        this.pseudo = pseudo;
    }

    public String getPseudo(){
        return pseudo;
    }


}
