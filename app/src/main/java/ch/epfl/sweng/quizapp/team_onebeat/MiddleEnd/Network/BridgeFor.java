package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.*;
import java.util.concurrent.Future;

/**
 * Created by hugo on 24.10.2015.
 * When a request is send to the bridge,
 * the bridge return a future of JSONOBJECT
 * bridgeFor is able to transform the Future<JSONOBJECT> into
 * a Future of T
 * T will be one of the class in the "RetrieveData" package,
 * so it's possible to make a BridgeFor<Authentification>
 */

public class BridgeFor<T> extends Bridge{

    private Parser<T> parser;
    private Bridge bridge;

    public BridgeFor(Parser<T> parser, Request request){
    super(request);

    }

    public Future<T> retrieveData( Request req ){
    return null;
    }

}
