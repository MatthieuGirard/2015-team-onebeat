package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.quizapp.team_onebeat.Util.Signature;

/**
 * Created by hugo on 25.10.2015.
 */
public class Request extends Message {


    public enum Available { EXIST_USER, SUBSCRIPTION, CONNECTION}
    private final Available selectedRequest;

    public static List<Message> historyTracker = new ArrayList<>();

    private Request(JSONObject message, Available selectedRequest) throws JSONException {
        super(message);
        this.selectedRequest = selectedRequest;
    }


    //______________________________________ STATIC FACTORY


    public static Request existUser( Signature signature ) throws JSONException{
        String message = "{\n"
                + "\"request\": \"" + Available.EXIST_USER.toString() +"\",\n"
                + "\"macAddress\": \"" + signature.value().getString("macAddress") + "\"\n"
                + "}\n";

        return new Request( new JSONObject(message), Available.EXIST_USER);
    }


    public static Request subscribe(Signature signature, String pseudo) throws JSONException{
        String message= "{\n"
                + "\"request\": \"" + Available.SUBSCRIPTION.toString() +"\",\n"
                + "\"macAddress\": \"" + signature.value().getString("macAddress") + "\",\n"
                + "\"pseudo\": \""+pseudo+"\"\n"
                + "}\n";
        return new Request(new JSONObject(message), Available.SUBSCRIPTION);
    }

    public static Request connect(Signature signature) throws JSONException{

        String message= "{\n"
                + "\"request\": \"" + Available.CONNECTION.toString() +"\",\n"
                + "\"macAddress\": \"" + signature.value().getString("macAddress") + "\"\n"
                + "}\n";


        return  new Request(new JSONObject(message), Available.CONNECTION);
    }

    @Override
    public List<Message> historyTracker(){
        return new ArrayList<>(messageTracker);
    }


}
