package ch.epfl.sweng.team_onebeat.MiddleEnd.Network;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hugo on 07.11.15.
 */
public class MessageFactory {


    private static LinkedList<Message> historyTracker = new LinkedList<>();



    public List<Message> historyTracker(){
        return new ArrayList<>(historyTracker);
    }


    private static Message trackAndReturn(Message message){
        historyTracker.push(message);
        Log.d("#Message", message.toString());

        return message;
    }


    public static LinkedList<Message> historyTacker(){
        return new LinkedList<>(historyTracker);
    }


    public static class Frontend {


        public static Message existUser( int signature ) throws JSONException {
            String message = "{\n"
                    + "\"request\": \" exist-user \",\n"
                    + "\"signature\": \"" + signature + "\"\n"
                    + "}\n";
            return trackAndReturn(new Message( new JSONObject(message) ));
        }





        public static Message subscribe(int signature, String pseudo) throws JSONException{
            String message= "{\n"
                    + "\"request\": \" subscription \",\n"
                    + "\"signature\": \"" + signature + "\",\n"
                    + "\"pseudo\": \""+pseudo+"\"\n"
                    + "}\n";
            return trackAndReturn(new Message(new JSONObject(message)));
        }





        public static Message connect(int signature) throws JSONException{

            String message= "{\n"
                    + "\"request\": \"connection\",\n"
                    + "\"macAddress\": \"" + signature + "\"\n"
                    + "}\n";


            return trackAndReturn(new Message(new JSONObject(message)));
        }




    }







    public static class Backend {


        public static Message bool( boolean bool ) throws JSONException {
            String message = "{\n"
                    + "\"boolean\": \" " + bool + " \",\n"
                    + "}\n";
            return trackAndReturn(new Message( new JSONObject(message) ));
        }



    }


}
