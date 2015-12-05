package ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.User;


/**
 * Created by hugo on 25.10.2015.
 *
 * Can parse a json response to a builder of UserData
 */
public class SubscribedPlaylistParser implements Parser<List<String>> {


    @Override
    public List<String> parse(JSONObject obj) throws ParseException, BackendCommunicationException {

        try {

            List<String> playlists = new ArrayList<>();
            JSONArray jsonArray = obj.getJSONArray("rooms");
            for(int i = 0; i< jsonArray.length(); i++){
                playlists.add(jsonArray.getJSONObject(i).getString("id"));
            }

            return playlists;

        } catch (JSONException e) {
            throw new ParseException();
        }

    }



}
