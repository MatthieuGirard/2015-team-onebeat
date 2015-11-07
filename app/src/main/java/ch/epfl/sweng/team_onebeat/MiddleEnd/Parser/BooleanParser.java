package ch.epfl.sweng.team_onebeat.MiddleEnd.Parser;

import org.json.JSONObject;

import java.text.ParseException;

import ch.epfl.sweng.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.team_onebeat.MiddleEnd.RetrieveData.BooleanData;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * Can parse a json response to a builder of BooleanData.
 *
 */
public class BooleanParser implements Parser<BooleanData>{ // TODO : ??


    @Override
    public BooleanData parse(JSONObject obj) throws ParseException {

        throw new NotImplementedException();
    }


}
