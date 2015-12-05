package ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat;

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

        String id = null;
        String name = null;
        try {
            id = obj.getString("id");
            name =obj.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new User(id, name);
    }


}
