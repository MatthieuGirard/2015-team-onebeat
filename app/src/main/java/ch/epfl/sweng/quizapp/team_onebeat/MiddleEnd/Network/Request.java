package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.quizapp.team_onebeat.Util.Signature;

/**
 * Created by hugo on 23.10.2015.
 *
 * Request create message needed to communicate with the backend.
 * it's constructor is private, request are created by a static factory builder.
 *
 */
public final class Request {



    private final JSONObject message;
    private final Type messageType;

    public enum Type { EXIST_USER, SUBSCRIPTION, CONNECTION}


    public static List<Request> requestHistoryTracker = new ArrayList<Request>();


    //______________________________________ PRIVATE CONSTRUCTOR

    private Request(JSONObject message, Type type) throws JSONException {
        this.message = message;
        this.messageType = type;
        requestHistoryTracker.add(this);
    }


    //______________________________________ STATIC FACTORY


    public static Request existUser( Signature signature ) throws JSONException{
        String message = "{\n"
                + "\"request\": \"" + Type.EXIST_USER.toString() +"\",\n"
                + "\"macAddress\": \"" + signature.value().getString("macAddress") + "\"\n"
                + "}\n";

        return new Request( new JSONObject(message), Type.EXIST_USER);
    }


    public static Request subscribe(Signature signature, String pseudo) throws JSONException{
        String message= "{\n"
                + "\"request\": \"" + Type.SUBSCRIPTION.toString() +"\",\n"
                + "\"macAddress\": \"" + signature.value().getString("macAddress") + "\",\n"
                + "\"pseudo\": \""+pseudo+"\"\n"
                + "}\n";
        return  new Request(new JSONObject(message), Type.SUBSCRIPTION);
    }

    public static Request connect(Signature signature) throws JSONException{

        String message= "{\n"
                + "\"request\": \"" + Type.CONNECTION.toString() +"\",\n"
                + "\"macAddress\": \"" + signature.value().getString("macAddress") + "\"\n"
                + "}\n";


        return  new Request(new JSONObject(message), Type.CONNECTION);
    }

    //______________________________________ ACCESSORS

    public JSONObject getMessage(){
        return message;
    }

    public List<Request> historyTracker(){
        return new ArrayList<>(requestHistoryTracker);
    }

    //______________________________________ OVERRIDE METHODS

    @Override
    public boolean equals(Object that){

        if(that instanceof Request){
            return ((Request)that).messageType == this.messageType;
        }

        return false;

    }

    @Override
    public int hashCode(){
        return messageType.ordinal();
    }


}

