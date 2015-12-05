package ch.epfl.sweng.onebeat;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.runner.RunWith;


/**
 * Created by hugo on 07.11.15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainControlerTest  {
/*

    // TODO : need to update

    private ControlerMainActivity controler;

    @Before
    public void start() {
        controler = Mockito.mock(ControlerMainActivity.class);
    }






    @Test
    public void spotifyConnectSuccess() throws JSONException, InterruptedException {

        StaticMachine.get(StaticMachine.Type.CONNECTION).setState(
                State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT)
        );

        // backend return true when a request for authentification is made
        // ( controler retrieve a pending data<Boolean> true after 1000ms)
        PendingData<BooleanData> pendingData = new PendingData<BooleanData>(new BooleanData(true), 1000);

        try {
            Mockito.doReturn(pendingData).when(BackendDataProvider.getInstance()).existUser(Mockito.any(Integer.class));

        }  catch (MalformedURLException e) {
            e.printStackTrace();
        }


        // verify spotify user request is sent

        // always in try state (1000ms delay)
        assertEquals(
                "the spotify answer take 1s in the test and connection is made before",
                StaticMachine.get(StaticMachine.Type.CONNECTION).getState(),
                State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT)
        );

        wait(1500);

        // spotify say connected : machine state go to CONNECTED
        assertEquals(
                "after receive spotify answer machine : CONNECTION need to go in CONNECTED state",
                StaticMachine.get(StaticMachine.Type.CONNECTION).getState(),
                State.Factory.provide(StaticMachine.ConnectionState.CONNECTED)
        );


    }




    @Test
    public void spotifyConnectFailure() throws JSONException, InterruptedException {

        StaticMachine.get(StaticMachine.Type.CONNECTION).setState(
                State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT)
        );

        // backend return true when a request for authentification is made
        // ( controler retrieve a pending data<Boolean> true after 1000ms)
        PendingData<BooleanData> pendingData = new PendingData<BooleanData>(new BooleanData(false), 1000);

        try {
            Mockito.doReturn(pendingData).when(backendDataProvider).existUser(Mockito.any(Integer.class));

        }  catch (MalformedURLException e) {
            e.printStackTrace();
        }


        // always in try state (1000ms delay)
        assertEquals(
                "the spotify answer take 1s in the test and connection is made before",
                StaticMachine.get(StaticMachine.Type.CONNECTION).getState(),
                State.Factory.provide(StaticMachine.ConnectionState.TRY_CONNECT)
        );

        wait(1500);

        // spotify say connected : machine state go to CONNECTED
        assertEquals(
                "after receive spotify answer machine : CONNECTION need to go in OFF state",
                StaticMachine.get(StaticMachine.Type.CONNECTION).getState(),
                State.Factory.provide(StaticMachine.ConnectionState.OFF)
        );


    }




*/

}