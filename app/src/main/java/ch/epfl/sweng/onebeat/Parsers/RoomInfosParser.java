package ch.epfl.sweng.onebeat.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.Room;
import ch.epfl.sweng.onebeat.RetrievedData.Song;
import ch.epfl.sweng.onebeat.RetrievedData.User;

/**
 * Created by M4ttou on 03.12.2015.
 */
public class RoomInfosParser implements Parser {
    @Override
    public Room parse(String JSONStringToParse) throws ParseException {

        Room newRoom;
        try {
            JSONObject json = new JSONObject(JSONStringToParse);
            String name = json.getString("name");
            String creator = json.getString("creator");
            JSONArray playlist = json.getJSONArray("playlist");
            JSONArray addedBy = json.getJSONArray("addedBy");
            Map<Song, User> songs = new HashMap<>();
            for (int i = 0; i < playlist.length(); i++) {

            }

        } catch (JSONException e) {
            throw new ParseException(e);
        }
    }
}
