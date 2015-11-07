package ch.epfl.sweng.team_onebeat.MiddleEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;


/**
 * Created by hugo on 23.10.2015.
 *
 * Message encapsulates a JSON Message
 *
 */
public class Message {




    private final JSONObject message;


    public Message(JSONObject message) throws JSONException {
        this.message = message;
    }



    public JSONObject getMessage(){
        return message;
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

