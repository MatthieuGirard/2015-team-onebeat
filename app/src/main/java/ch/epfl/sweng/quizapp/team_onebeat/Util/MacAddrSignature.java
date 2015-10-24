package ch.epfl.sweng.quizapp.team_onebeat.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 24.10.2015.
 * create a signature used to identify someone
 * on the bdd. At this point, we use mac address.
 *
 */
public final class MacAddrSignature implements Signature {

    private Device device;

    public MacAddrSignature(Device device){
    this.device = device;
    }

    @Override
    public JSONObject auth() throws JSONException {
        return new JSONObject("");
    }

}
