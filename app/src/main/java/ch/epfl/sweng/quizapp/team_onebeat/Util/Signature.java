package ch.epfl.sweng.quizapp.team_onebeat.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 24.10.2015.
 * create a signature used to identify someone
 * on the bdd.
 */
public interface Signature {

    JSONObject auth() throws JSONException;
}
