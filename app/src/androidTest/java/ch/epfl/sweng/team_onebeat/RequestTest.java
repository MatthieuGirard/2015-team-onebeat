package ch.epfl.sweng.quizapp.team_onebeat;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.quizapp.team_onebeat.FrontEnd.Activity.MainActivity;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network.Request;
import ch.epfl.sweng.quizapp.team_onebeat.Util.Device;
import ch.epfl.sweng.quizapp.team_onebeat.Util.MacAddrSignature;


public class RequestTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public RequestTest() {
        super(MainActivity.class);
    }

    public void testSubscribeMessage() throws JSONException{

        String subscribe= "{\n"
                + "\"request\": \"" + Request.Available.SUBSCRIPTION.toString() +"\",\n"
                + "\"macAddress\": \"" + new Device(this.getActivity()).macAddress()+ "\",\n"
                + "\"pseudo\": \"hugo\"\n"
                + "}\n";
        Log.d("qwer2", subscribe);

        assertTrue("request for subscription : malformed",
                Request.subscribe(new MacAddrSignature(new Device(this.getActivity())), "hugo")
                        .getMessage()
                        .toString()
                        .equals(new JSONObject(subscribe).toString()) );
    }


    public void testConnectMessage()throws JSONException{

        String connection = "{\n"
                + "\"request\": \""+Request.Available.CONNECTION+"\",\n"
                + "\"macAddress\": \"" + new Device(this.getActivity()).macAddress()+ "\"\n"
                + "}\n";

        assertTrue("request for connection : malformed",
                Request.connect(new MacAddrSignature(new Device(this.getActivity())))
                        .getMessage()
                        .toString()
                        .equals(new JSONObject(connection).toString()));

    }

    public void testExistUser() throws JSONException{

        String existUser= "{\n"
                + "\"request\": \""+Request.Available.EXIST_USER+"\",\n"
                + "\"macAddress\": \"" + new Device(this.getActivity()).macAddress()+ "\"\n"
                + "}\n";
        Log.d("qwer", existUser);
        assertTrue("request for testing if a user exist in the bdd : malformed",
                Request.existUser(new MacAddrSignature(new Device(this.getActivity())))
                        .getMessage()
                        .toString()
                        .equals(new JSONObject(existUser).toString()));

    }



}