package ch.epfl.sweng.quizapp.team_onebeat.FrontEnd.MachineState;

import java.util.Observable;

/**
 * Created by hugo on 25.10.2015.
 * Keep track about the authentification state.
 */
public class Authentification extends Observable {

    public enum ConnectionState {INITIAL, SIGNATURE_AVAILABLE,SIGNATURE_EXISTING,
                                TRY_CONNECT, TRY_SUBSCRIBE,CONNECTED  }

    private static Authentification instance = new Authentification();
    private static ConnectionState state = ConnectionState.INITIAL;

    private Authentification(){
    if(instance != null){
        throw new InstantiationError();
    }
    }

    public Authentification oneInstance(){
        return instance;
    }

    public void setState(ConnectionState state){
        this.state = state;
    }



}
