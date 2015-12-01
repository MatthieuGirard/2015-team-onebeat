package ch.epfl.sweng.onebeat.Network;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Exceptions.ParserNotDefinedException;
import ch.epfl.sweng.onebeat.Parsers.Parser;
import ch.epfl.sweng.onebeat.Parsers.SongFromSearchParser;
import ch.epfl.sweng.onebeat.Parsers.SpotifyUserInfosParser;

/**
 * Created by hugo on 20.11.15.
 */
public class SpotifyDataProvider implements WebPageDownloader {

    private DataProviderObserver callingActivity;
    private Parser parser = null;

    public SpotifyDataProvider(DataProviderObserver callingDude) {
        this.callingActivity = callingDude;
    }

    @Override
    public void onWebDataReception(String result) throws ParseException, ParserNotDefinedException {
        if (parser != null) {
            callingActivity.onDataReception(parser.parse(result));
        } else {
            throw new ParserNotDefinedException();
        }
    }

    public void getListOfSongs(String searchInput) {
        parser = new SongFromSearchParser();

        String stringUrl = "http://ws.spotify.com/search/1/track.json?q=" + searchInput.replace(" ", "%20");

        new DownloadWebpageTask(this).execute(stringUrl, "");
    }

    public void getUserInfos(String token) {
        parser = new SpotifyUserInfosParser();
        new DownloadWebpageTask(this).execute("https://api.spotify.com/v1/me", token);
    }
}
