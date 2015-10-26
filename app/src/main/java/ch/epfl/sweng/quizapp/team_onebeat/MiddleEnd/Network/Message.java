package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.quizapp.team_onebeat.Util.Signature;

/**
 * Created by hugo on 23.10.2015.
 *
 * Request create message needed to communicate with the backend.
 * it's constructor is private, request are created by a static factory builder.
 *
 */
public class Message {




    private final JSONObject message;
    public static List<Message> messageTracker = new ArrayList<>();



    public Message(JSONObject message) throws JSONException {
        this.message = message;
        messageTracker.add(this);
    }



    public JSONObject getMessage(){
        return message;
    }



    public List<Message> historyTracker(){
        return new ArrayList<>(messageTracker);
    }




    @Override
    public boolean equals(Object that){

        if(that instanceof Message){
            return ((Message)that).message.toString() == this.message.toString();
        }
        throw new NotImplementedException();

    }

    @Override
    public int hashCode(){
        return message.toString().hashCode();
    }


    @Override
    public String toString(){
        return message.toString();
    }


}

