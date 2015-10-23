package ch.epfl.sweng.quizapp.team_onebeat;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;


/**
 * Created by hugo on 23.10.2015.
 */
public class DeviceInformationTest extends ActivityInstrumentationTestCase2<MainActivity> {


    public DeviceInformationTest() {
        super(MainActivity.class);
    }

    @Test
    public void testMacAddressNonEmpty(){
        assertTrue("mac address empty", DeviceInformation.getInstance().macAddress(this.getActivity()) != "");
    }



}
