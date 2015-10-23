package ch.epfl.sweng.quizapp.team_onebeat;

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

    public static Request existUser(){
        return "";
    }

    public static Request subscribe(){
        return "";
    }

    public static Request connect(){
        return "";
    }


    //______________________________________ ACCESSOR

    public JSONObject getMessage(){
        return "";
    }


}

