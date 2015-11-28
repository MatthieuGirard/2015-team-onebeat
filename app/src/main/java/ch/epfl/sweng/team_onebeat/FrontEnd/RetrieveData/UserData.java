package ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData;

/**
 * Created by hugo on 24.10.2015.
 *
 * use case :
 * encapsulate a User coming from the backend
 *
 */
public final class UserData {

    private int id;
    private String pseudo;

    public UserData( int id, String pseudo ){
        this.id = id;
        this.pseudo = pseudo;
    }



    public String getPseudo(){
        return pseudo;
    }


    public int getId(){
        return id;
    }


}
