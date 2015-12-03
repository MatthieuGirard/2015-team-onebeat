package ch.epfl.sweng.onebeat.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.Room;

/**
 * Created by M4ttou on 03.12.2015.
 */
public class ListOfRoomsParser implements Parser {
    @Override
    public List<Room> parse(String JSONStringToParse) throws ParseException {
        try {
            JSONObject json = new JSONObject(JSONStringToParse);
            //TODO
            return null;
        } catch (JSONException e) {
            throw new ParseException(e);
        }
    }
}
