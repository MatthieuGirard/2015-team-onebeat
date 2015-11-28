package ch.epfl.sweng.team_onebeat.FrontEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.team_onebeat.Exceptions.ParseException;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.IntegerData;

/**
 * Created by hugo on 27.11.15.
 */
public class IdentifierParser implements Parser<IntegerData>{


    @Override
    public IntegerData parse(JSONObject obj) throws ParseException {

        try {
            int id = obj.getInt("id");
        } catch (JSONException e) {
            throw new ParseException();
        }


        return null;
    }




}
