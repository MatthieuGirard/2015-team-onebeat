package ch.epfl.sweng.onebeat.Parsers;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;

/**
 * Created by Matthieu on 02.12.2015.
 */
public class CreateRoomParser implements Parser {

    @Override
    public String parse(String JSONStringToParse) throws ParseException {
        return JSONStringToParse;
    }
}
