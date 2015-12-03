package ch.epfl.sweng.onebeat.Network;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Exceptions.ParserNotDefinedException;
import ch.epfl.sweng.onebeat.Parsers.Parser;

/**
 * Created by Matthieu on 02.12.2015.
 */
public abstract class DataProvider {
    private DataProviderObserver callingActivity;
    private Parser parser = null;
    private RequestTypes requestType = RequestTypes.CREATE_ROOM;

    public enum RequestTypes {GET_SPOTIFY_USER, GET_LIST_OF_SPOTIFY_SONGS, CREATE_ROOM };

    public DataProvider(DataProviderObserver callingDude) {
        this.callingActivity = callingDude;
    }

    public void onWebDataReception(String result) throws ParseException, ParserNotDefinedException {
        if (parser != null) {
            callingActivity.onDataReception(parser.parse(result), requestType);
        } else {
            throw new ParserNotDefinedException();
        }
    }

    public void setParser(Parser parser) { this.parser = parser; }
    public void setRequestType(RequestTypes requestType) { this.requestType = requestType; }
}
