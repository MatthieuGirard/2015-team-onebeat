package ch.epfl.sweng.quizapp.team_onebeat.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hugo on 24.10.2015.
 *
 * signature is used to identify someone on the bdd.
 */
public interface Signature {

    JSONObject value() throws JSONException;
}
