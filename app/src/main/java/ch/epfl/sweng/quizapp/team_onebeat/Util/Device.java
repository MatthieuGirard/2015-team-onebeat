package ch.epfl.sweng.quizapp.team_onebeat.Util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by hugo on 23.10.2015.
 * Singleton class about device information
 * can provide information about the user phone
 */
public final class Device {

    private static final Device oneInstance = new Device();

    private Device() {
        if(oneInstance != null) {
            throw new IllegalStateException();
        }
    }


    public static Device getInstance() {
        return oneInstance;
    }


    public String macAddress(Context context){
        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        return info.getMacAddress();
    }



}
