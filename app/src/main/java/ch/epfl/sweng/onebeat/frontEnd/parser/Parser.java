package ch.epfl.sweng.onebeat.frontEnd.parser;

import org.json.JSONObject;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;


/**
 * Created by hugo on 24.10.2015.
 * Can transform JSONObject into a specified class T
 */
public interface Parser<T> {

    T parse(JSONObject obj) throws ParseException, BackendCommunicationException;

}
