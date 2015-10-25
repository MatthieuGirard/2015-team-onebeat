package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.BooleanData;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.Buildable;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.UserData;

/**
 * Created by hugo on 25.10.2015.
 *
 * Can transform a Json response into a user data
 */
public class UserParser<T extends Buildable<UserData>> implements Parser<Buildable<UserData>> {


    @Override
    public  T parse(JSONObject obj) throws JSONException{
        return null;
    }
}
