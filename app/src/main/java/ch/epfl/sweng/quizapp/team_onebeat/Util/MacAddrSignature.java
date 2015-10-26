package ch.epfl.sweng.quizapp.team_onebeat.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 24.10.2015.
 *
 * signature is used to identify someone on the bdd.
 * At this point, we use mac address.
 *
 */
public final class MacAddrSignature implements Signature {

    private Device device;

    public MacAddrSignature(Device device){
    this.device = device;
    }


    @Override
    public JSONObject value() throws JSONException {

        return new JSONObject("{\n"
                            + "\"macAddress\": \"" + device.macAddress()+ "\"\n"
                            + "}\n");
    }


}
