package ch.epfl.sweng.onebeat.Parsers;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.UserData;

/**
 * Created by hugo on 25.10.2015.
 *
 * Can parse a json response to a builder of UserData
 */
public class UserParser implements Parser<UserData> {


    @Override
    public UserData parse(String JSONStringToParse) throws ParseException {
        return null;
    }

    @Override
    public  UserData parse(JSONObject obj) throws ParseException {

        try {
            int id = obj.getInt("id");
            String pseudo = obj.getString("pseudo");

            return new UserData(id, pseudo);

        } catch (JSONException e) {
            throw new ParseException();
        }




    }

/*

    //JSON3 : user
    {
        "info" : "user",
            "id" : 151452435,
            "pseudo" : "Hugo"
    }

    */
}
