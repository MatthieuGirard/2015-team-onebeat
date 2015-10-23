package ch.epfl.sweng.quizapp.team_onebeat;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 23.10.2015.
 *
 * Request create message needed to communicate
 * with the backend.
 * it's constructor is private, request are created by a static factory builder.
 *
 */
public class Request {


    //______________________________________ CONSTRUCTOR


    private JSONObject message;


    private Request(JSONObject message){
        this.message = message;
    }



    //______________________________________ MAC

    private String macAddr() {
    return "";
    }


    //______________________________________ FACTORY

    public static Request existUser() throws JSONException{
        return new Request(new JSONObject(""));
    }

    public static Request subscribe() throws JSONException{
        return  new Request(new JSONObject(""));
    }

    public static Request connect() throws JSONException{
        return  new Request(new JSONObject(""));
    }


    //______________________________________ ACCESSOR

    public JSONObject getMessage(){
        return message;
    }


}

