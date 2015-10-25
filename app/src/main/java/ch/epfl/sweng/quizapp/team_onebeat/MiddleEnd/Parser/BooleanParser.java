package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.BooleanData;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.RetrieveBuildableData;

/**
 * Created by hugo on 25.10.2015.
 *
 * Can parse a json response to a builder of BooleanData.
 * Boolean are encapsulate for modularity (information about backend transaction ?)
 */
public class BooleanParser implements Parser<RetrieveBuildableData<BooleanData>>{ // TODO : ??


    @Override
    public RetrieveBuildableData<BooleanData> parse(JSONObject obj) throws ParseException {
        return null;
    }


}
