package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by hugo on 24.10.2015.
 * Can transform JSONObject into a specified class T
 */
public interface Parser<T> {

    T parse(JSONObject obj) throws ParseException;

}
