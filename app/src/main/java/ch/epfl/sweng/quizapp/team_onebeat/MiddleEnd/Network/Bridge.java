package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;

import org.json.JSONObject;

import java.util.concurrent.Future;

/**
 * Created by hugo on 24.10.2015.
 * Bridge is a link between the middle end/back end.
 * it can send request and return Future<JSONObject>
 */

public class Bridge {

    public Bridge(Request request){
    }

    public void sendRequest(){

    }

    public Future<JSONObject> response(Request req){
        sendRequest();
        return null;
    }

}
