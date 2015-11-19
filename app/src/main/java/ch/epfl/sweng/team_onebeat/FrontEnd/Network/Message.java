package ch.epfl.sweng.team_onebeat.FrontEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by hugo on 25.10.2015.
 *
 * Request create message needed to communicate with the backend.
 * it's constructor is private, request are created by a static factory builder.
 */


public class Message {



    private JSONObject message;



    public Message(JSONObject message) throws JSONException {
        this.message = message;
    }



    @Override
    public String toString(){
        return message.toString();
    }


    @Override
    public boolean equals( Object that ){

        if( !(that instanceof Message) ) {
            return false;
        }

        return that.toString() == this.toString();

    }


    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }



}
