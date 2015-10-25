package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.BooleanData;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.Buildable;

/**
 * Created by hugo on 25.10.2015.
 *
 * Can parse a json response to a boolean data
 */
public class BooleanParser<T extends Buildable<BooleanData>> implements Parser<Buildable<BooleanData>>{ // TODO : ??


    @Override
    public T parse(JSONObject obj) throws JSONException {
        return null;
    }


}
