package ch.epfl.sweng.quizapp.team_onebeat;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by hugo on 23.10.2015.
 */

public class DeviceInformationTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public DeviceInformationTest() {
        super(MainActivity.class);
    }

    public void testMacAddressNonEmpty(){
        assertTrue("mac address empty", DeviceInformation.getInstance().macAddress(this.getActivity()) != "");
    }



}
