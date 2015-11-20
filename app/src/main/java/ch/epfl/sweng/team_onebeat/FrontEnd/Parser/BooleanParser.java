package ch.epfl.sweng.team_onebeat.FrontEnd.Parser;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.Exceptions.ParseException;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.BooleanData;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * Can parse a json response to BooleanData.
 *
 */
public class BooleanParser implements Parser<BooleanData>{


    @Override
    public BooleanData parse(JSONObject obj) throws ParseException {

        try {

            boolean bool = obj.getBoolean("boolean");
            return new BooleanData(bool);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        throw new NotImplementedException();
    }


}
