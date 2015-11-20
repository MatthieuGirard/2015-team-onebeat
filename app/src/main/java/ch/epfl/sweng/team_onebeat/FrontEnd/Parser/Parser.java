package ch.epfl.sweng.team_onebeat.FrontEnd.Parser;

import org.json.JSONObject;

import ch.epfl.sweng.team_onebeat.Exceptions.ParseException;


/**
 * Created by hugo on 24.10.2015.
 * Can transform JSONObject into a specified class T
 */
public interface Parser<T> {

    T parse(JSONObject obj) throws ParseException;

}
