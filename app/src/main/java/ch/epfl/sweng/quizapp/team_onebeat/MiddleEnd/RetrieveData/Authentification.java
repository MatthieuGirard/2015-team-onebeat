package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import ch.epfl.sweng.quizapp.team_onebeat.Util.Signature;

/**
 * Created by hugo on 24.10.2015.
 * process the authentification
 * it's observe the View and block on it ?
 * the view observe the Auth for displaying "connected"
 *
 */
public final class Authentification {

    private boolean isConnected = false;
    private User user;

    public Authentification(Signature signature){
        process(signature);
    }


    private void process(Signature signature){
        // exist user ?
        // no => subscribe / modularity with GUI - args  ?
        // yes => make connection and retrieve user
    }

    public boolean existUser(){
        // define a bridge for bool / block until future ready / and return value
        return false;
    }

    public boolean subscribe(){
        // define a bridge for bool / block until future ready / and return value
        return false;
    }

    public boolean connection(){
        // define a bridge for bool / block until future ready / and return value
        return false;
    }



    public boolean isConnected(){
        return isConnected;
    }


    public User getUser(){
        return user;
    }



}
