package ch.epfl.sweng.onebeat.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.IntegerData;

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
