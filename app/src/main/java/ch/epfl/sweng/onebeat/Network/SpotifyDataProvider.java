package ch.epfl.sweng.onebeat.Network;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Parsers.SongFromSearchParser;

/**
 * Created by hugo on 20.11.15.
 */
public class SpotifyDataProvider implements WebPageDownloader {

    DataProviderObserver callingActivity;

    public SpotifyDataProvider(DataProviderObserver callingDude) {
        this.callingActivity = callingDude;
    }

    @Override
    public void onWebDataReception(String result) throws ParseException {
        callingActivity.onDataReception(new SongFromSearchParser().parse(result));
    }

    public void getListOfSongs(String searchInput) {
        String stringUrl = "http://ws.spotify.com/search/1/track.json?q=" + searchInput.replace(" ", "%20");

        new DownloadWebpageTask(this).execute(stringUrl, "");
    }

    public void getUserInfos(String token) {
        new DownloadWebpageTask(this).execute("https://api.spotify.com/v1/me", token);
    }
}
