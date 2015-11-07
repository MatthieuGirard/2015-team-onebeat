package ch.epfl.sweng.team_onebeat.MiddleEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hugo on 25.10.2015.
 *
 * Request create message needed to communicate with the backend.
 * it's constructor is private, request are created by a static factory builder.
 */


public class Request extends Message {


    public enum Available { EXIST_USER, SUBSCRIPTION, CONNECTION}
    private final Available selectedRequest;

    public static List<Message> historyTracker = new ArrayList<>();

    private Request(JSONObject message, Available selectedRequest) throws JSONException {
        super(message);
        this.selectedRequest = selectedRequest;
    }


    public static class Factory {


    public static Request existUser( int signature ) throws JSONException{
        String message = "{\n"
                + "\"request\": \"" + Available.EXIST_USER.toString() +"\",\n"
                + "\"signature\": \"" + signature + "\"\n"
                + "}\n";

        return new Request( new JSONObject(message), Available.EXIST_USER);
    }


    public static Request subscribe(int signature, String pseudo) throws JSONException{
        String message= "{\n"
                + "\"request\": \"" + Available.SUBSCRIPTION.toString() +"\",\n"
                + "\"signature\": \"" + signature + "\",\n"
                + "\"pseudo\": \""+pseudo+"\"\n"
                + "}\n";
        return new Request(new JSONObject(message), Available.SUBSCRIPTION);
    }

    public static Request connect(int signature) throws JSONException{

        String message= "{\n"
                + "\"request\": \"" + Available.CONNECTION.toString() +"\",\n"
                + "\"macAddress\": \"" + signature + "\"\n"
                + "}\n";


        return  new Request(new JSONObject(message), Available.CONNECTION);
    }



    }


    public List<Message> historyTracker(){
        return new ArrayList<>(historyTracker);
    }

    @Override
    public String toString(){
        return selectedRequest.name();
    }

}
