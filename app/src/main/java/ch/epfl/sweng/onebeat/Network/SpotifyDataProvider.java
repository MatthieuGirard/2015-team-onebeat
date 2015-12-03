package ch.epfl.sweng.onebeat.Network;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Exceptions.ParserNotDefinedException;
import ch.epfl.sweng.onebeat.Parsers.Parser;
import ch.epfl.sweng.onebeat.Parsers.SongFromSearchParser;
import ch.epfl.sweng.onebeat.Parsers.SpotifyUserInfosParser;

/**
 * Created by hugo on 20.11.15.
 */
public class SpotifyDataProvider extends DataProvider {

    public SpotifyDataProvider(DataProviderObserver callingDude) {
        super(callingDude);
    }

    public void getListOfSongs(String searchInput) {
        super.setParser(new SongFromSearchParser());
        super.setRequestType(RequestTypes.GET_LIST_OF_SPOTIFY_SONGS);

        String stringUrl = "http://ws.spotify.com/search/1/track.json?q=" + searchInput.replace(" ", "%20");

        new DownloadWebpageTask(this).execute(stringUrl, "");
    }

    public void getUserInfos(String token) {
        super.setParser(new SpotifyUserInfosParser());
        super.setRequestType(RequestTypes.GET_SPOTIFY_USER);

        new DownloadWebpageTask(this).execute("https://api.spotify.com/v1/me", token);

    }
}
