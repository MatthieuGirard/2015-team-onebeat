package ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;

/**
 * Created by hugo on 03.12.15.
 */
public class PostAddedToBooleanParser implements Parser<Boolean> {


    @Override
    public Boolean parse(JSONObject obj) throws ParseException, BackendCommunicationException {

        try {

            return obj.getBoolean("added");

        } catch (JSONException e) {
            throw new BackendCommunicationException();
        }

    }



}


