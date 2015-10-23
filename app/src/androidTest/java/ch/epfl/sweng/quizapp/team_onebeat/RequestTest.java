package ch.epfl.sweng.quizapp.team_onebeat;

import android.test.ActivityInstrumentationTestCase2;

import org.json.JSONException;
import org.json.JSONObject;


public class RequestTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public RequestTest() {
        super(MainActivity.class);
    }

    public void testSubscribeMessage() throws JSONException{
        String macAddr = DeviceInformation.getInstance().macAddress(this.getActivity());
        String subscribe= "{\n"
                + "\"request\": \"" + Request.Type.SUBSCRIPTION.toString() +"\",\n"
                + "\"macAddress\": \"" + macAddr + "\",\n"
                + "\"pseudo\": \"hugo\"\n"
                + "}\n";

        assertTrue("request for subscription : malformed",
                Request.subscribe(macAddr, "hugo")
                        .getMessage()
                        .toString()
                        .equals(new JSONObject(subscribe).toString()) );
    }


    public void testConnectMessage()throws JSONException{

        String macAddr = DeviceInformation.getInstance().macAddress(this.getActivity());

        String connection = "{\n"
                + "\"request\": \""+Request.Type.CONNECTION+"\",\n"
                + "\"macAddress\": \"" + macAddr + "\"\n"
                + "}\n";

        assertTrue("request for connection : malformed",
                Request.connect(macAddr)
                        .getMessage()
                        .toString()
                        .equals(new JSONObject(connection).toString()));

    }

    public void testExistUser() throws JSONException{

        String macAddr = DeviceInformation.getInstance().macAddress(this.getActivity());

        String existUser= "{\n"
                + "\"request\": \""+Request.Type.EXIST_USER+"\",\n"
                + "\"macAddress\": \"" + macAddr + "\"\n"
                + "}\n";

        assertTrue("request for testing if a user exist in the bdd : malformed",
                Request.existUser(macAddr)
                        .getMessage()
                        .toString()
                        .equals(new JSONObject(existUser).toString()));

    }



}