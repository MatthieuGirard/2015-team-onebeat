package ch.epfl.sweng.team_onebeat.FrontEnd.Network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 07.11.15.
 */
public class MessageFactory {


    public static class Frontend {


        public static Message existUser( int signature ) throws JSONException {
            String message = "{\n"
                    + "\"request\": \" exist-user \",\n"
                    + "\"signature\": \"" + signature + "\"\n"
                    + "}\n";
            return new Message( new JSONObject(message));
        }





        public static Message subscribe(int signature, String pseudo) throws JSONException{
            String message= "{\n"
                    + "\"request\": \" subscription \",\n"
                    + "\"signature\": \"" + signature + "\",\n"
                    + "\"pseudo\": \""+pseudo+"\"\n"
                    + "}\n";
            return new Message(new JSONObject(message));
        }





        public static Message connect(int signature) throws JSONException{

            String message= "{\n"
                    + "\"request\": \"connection\",\n"
                    + "\"macAddress\": \"" + signature + "\"\n"
                    + "}\n";


            return new Message(new JSONObject(message));
        }


        public static Message spotifyConnect(String login, int password) throws JSONException{

            // TODO
            String message= "{\n"
                    + "\"request\": \"spotify-connect\",\n"
                    + "\"login\": \"" + login + "\"\n"
                    + "\"password\": \"" + password + "\"\n"
                    + "}\n";


            return new Message(new JSONObject(message));
        }




    }







    public static class Backend {


        public static Message bool( boolean bool ) throws JSONException {
            String message = "{\n"
                    + "\"boolean\": \" " + bool + " \",\n"
                    + "}\n";
            return new Message( new JSONObject(message) );
        }



    }


}
