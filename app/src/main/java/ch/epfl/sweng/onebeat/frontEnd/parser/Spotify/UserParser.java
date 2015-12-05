package ch.epfl.sweng.onebeat.frontEnd.parser.Spotify;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.User;

/**
 * Created by hugo on 05.12.15.
 */
public class UserParser implements Parser<User> {


    @Override
    public User parse(JSONObject obj) throws ParseException, BackendCommunicationException {

        String id;
        String name;
        try {
           id  = obj.getString("id");
           name = obj.getString("display_name");
        } catch (JSONException e) {
            throw new ParseException();
        }

        return new User(id, name);
    }


}
