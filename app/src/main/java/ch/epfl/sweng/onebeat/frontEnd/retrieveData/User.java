package ch.epfl.sweng.onebeat.frontEnd.retrieveData;

/**
 * Created by hugo on 24.10.2015.
 *
 * use case :
 * encapsulate a User coming from spotify
 *
 */
public final class User {

    private String id;
    private String pseudo;

    public User( String id, String pseudo ){
        this.id = id;
        this.pseudo = pseudo;
    }



    public String getPseudo(){
        return pseudo;
    }

    public String getId(){
        return id;
    }

    public User setPseudo(String newPseudo){
        return new User(id, newPseudo);
    }



}
