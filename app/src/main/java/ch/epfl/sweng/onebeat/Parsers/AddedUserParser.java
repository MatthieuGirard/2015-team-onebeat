package ch.epfl.sweng.onebeat.Parsers;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.IntegerData;

/**
 * Created by hugo on 30.11.15.
 */
public class AddedUserParser implements Parser<IntegerData> {


    @Override
    public IntegerData parse(String JSONStringToParse) throws ParseException {
        return null;
    }

    @Override
    public IntegerData parse(JSONObject obj) throws ParseException {


        try {
            boolean added = obj.getBoolean("added");
            if(added){
                return new IntegerData(obj.getInt("id"));
            }
            else {
                return new IntegerData(0).setError();
            }


        } catch (JSONException e) {
            throw new ParseException();
        }



    }




}
