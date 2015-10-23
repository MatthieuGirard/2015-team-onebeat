package ch.epfl.sweng.quizapp.team_onebeat;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class RequestTest {



    private static final Context CONTEXT = new MainActivity();

    private static final String JSON_SUBSCRIBE= "{\n"
            + "  \"request\": \"subscribe\",\n"
            + "  \"macAddress\": \" " + DeviceInformation.getInstance().macAddress(CONTEXT) + " \",\n"
            + "  \"pseudo\": \" hugo \" [\n"
            + "}\n";

    private static final String JSON_CONNECT= "{\n"
            + "  \"request\": \"connect\",\n"
            + "  \"macAddress\": \" " + DeviceInformation.getInstance().macAddress(CONTEXT) + " \",\n"
            + "}\n";

    private static final String JSON_EXIST_USER= "{\n"
            + "  \"request\": \"exist_user\",\n"
            + "  \"macAddress\": \" " + DeviceInformation.getInstance().macAddress(CONTEXT) + " \",\n"
            + "}\n";


@Test
public void subscribeMessage() throws JSONException{

    assertEquals("message for subscription malformed", Request.subscribe().getMessage(), new JSONObject(JSON_SUBSCRIBE));

}

@Test
public void connectMessage()throws JSONException{

    assertEquals("message for connection malformed", Request.connect().getMessage(), new JSONObject(JSON_CONNECT));

}

@Test
public void existUser() throws JSONException{

    assertEquals("message for testing user mac address on bdd malformed", Request.existUser().getMessage(), new JSONObject(JSON_EXIST_USER));

}



}