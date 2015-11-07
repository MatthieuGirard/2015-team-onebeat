package ch.epfl.sweng.team_onebeat;

import android.test.ActivityInstrumentationTestCase2;

import org.json.JSONException;
import org.junit.Test;

import java.util.LinkedList;

import ch.epfl.sweng.team_onebeat.FrontEnd.Activity.MainActivity;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.ControlerMainActivity;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.MachineState;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.State;
import ch.epfl.sweng.team_onebeat.FrontEnd.Controler.StaticMachine;
import ch.epfl.sweng.team_onebeat.MiddleEnd.Network.Message;
import ch.epfl.sweng.team_onebeat.MiddleEnd.Network.MessageFactory;
import ch.epfl.sweng.team_onebeat.Util.BackendSimulator;

/**
 * Created by hugo on 07.11.15.
 */
public class MainControlerTest extends ActivityInstrumentationTestCase2<MainActivity>  {



    private ControlerMainActivity controler;


    public MainControlerTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }



    @Test
    public void firstConnection(){


        MachineState machine = StaticMachine.get(StaticMachine.Type.CONNECTION);



        this.getActivity().findViewById(R.id.button).performClick();

        assertEquals(
                "after clicking on button, connection state need to be in try_connect state",
                StaticMachine.get(StaticMachine.Type.CONNECTION).getState(),
                StaticMachine.ConnectionState.TRY_CONNECT
        );



        LinkedList<Message> history = MessageFactory.historyTacker();
        try {

            assertEquals(
                    " message : 3 in try_connect mode is a connect request",
                    history.pop(),
                    MessageFactory.Frontend.connect(1234));

            assertEquals(
                    " message : 2 in try_connect mode is a subscribe request",
                    history.pop(),
                    MessageFactory.Frontend.subscribe(1234,"hugo"));

            assertEquals(
                    "message : 3 in try_connect mode is a exist-user request",
                    history.pop(),
                    MessageFactory.Frontend.existUser(1234));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        machine.setState(State.Factory.provide(StaticMachine.ConnectionState.CONNECTED));

        // TODO : verify RoomActivity is started

    }





}
