package ch.epfl.sweng.quizapp.team_onebeat.Util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by hugo on 23.10.2015.
 *
 * Singleton class about device information
 * can provide information about the user phone
 */
public final class Device {

    private Context context;

    public Device(Context context) {
        this.context = context;
    }


    public String macAddress(){
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }



}
