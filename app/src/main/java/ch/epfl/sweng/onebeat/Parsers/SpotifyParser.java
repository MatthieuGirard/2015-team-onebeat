package ch.epfl.sweng.onebeat.Parser;

import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;

/**
 * Created by hugo on 08.11.15.
 */
public class SpotifyParser implements Parser<SpotifyUser> {

    @Override
    public SpotifyUser parse(JSONObject obj) throws ParseException {

        // TODO
        throw new NotImplementedException();

    }
}
