package ch.epfl.sweng.quizapp.team_onebeat;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hugo on 23.10.2015.
 */

public class DeviceInformationTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public DeviceInformationTest() {
        super(MainActivity.class);
    }

    public void testMacAddressNonEmpty(){
        assertTrue("mac address empty", Device.getInstance().macAddress(this.getActivity()) != "");
    }



}
