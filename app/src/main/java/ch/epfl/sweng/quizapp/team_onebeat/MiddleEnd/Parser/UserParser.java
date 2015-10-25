package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.RetrieveBuildableData;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.UserData;

/**
 * Created by hugo on 25.10.2015.
 *
 * Can parse a json response to a builder of UserData
 */
public class UserParser implements Parser<UserData> {


    @Override
    public  UserData parse(JSONObject obj) throws ParseException{
        return null;
    }
}
