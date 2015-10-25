package ch.epfl.sweng.quizapp.team_onebeat;

import android.test.ActivityInstrumentationTestCase2;

import ch.epfl.sweng.quizapp.team_onebeat.FrontEnd.MainActivity;
import ch.epfl.sweng.quizapp.team_onebeat.Util.Device;

/**
 * Created by hugo on 23.10.2015.
 */

public class DeviceInformationTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public DeviceInformationTest() {
        super(MainActivity.class);
    }

    public void testMacAddressNonEmpty(){
        Device device = new Device(this.getActivity());
        assertTrue("mac address empty", device.macAddress() != "");
    }



}
