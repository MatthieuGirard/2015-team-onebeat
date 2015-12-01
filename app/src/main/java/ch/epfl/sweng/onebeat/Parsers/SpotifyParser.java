package ch.epfl.sweng.onebeat.Parsers;

import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Parsers.Parser;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;

/**
 * Created by hugo on 08.11.15.
 */
public class SpotifyParser implements Parser<SpotifyUser> {

    @Override
    public SpotifyUser parse(String JSONStringToParse) throws ParseException {
        return null;
    }

    @Override
    public SpotifyUser parse(JSONObject obj) throws ParseException {

        // TODO
        throw new NotImplementedException();

    }
}
