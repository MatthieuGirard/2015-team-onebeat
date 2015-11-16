package ch.epfl.sweng.spotifytests;

import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthieu on 16.11.2015.
 */
public class JSONParser {

    static public List<Track> parseFromSearchAPI(String jsonStringToParse) throws JSONParserException {

        try {
            JSONObject json = new JSONObject(jsonStringToParse);
            JSONObject info = json.getJSONObject("info");

            int numResults = info.getInt("num_results");
            int numPages = info.getInt("page");

            JSONArray jsonTracks = json.optJSONArray("tracks");

            List<Track> tracksFound = new ArrayList<>();

            for (int i = 0; i < jsonTracks.length(); i++) {
                JSONObject actualTrack = jsonTracks.getJSONObject(i);

                String artist = actualTrack.getJSONArray("artists").getJSONObject(0).getString("name");
                String songName = actualTrack.getString("name");
                String spotifyRef = actualTrack.getString("href");

                tracksFound.add(new Track(artist, songName, spotifyRef));
            }
            return tracksFound;
        } catch (JSONException e) {
            throw new JSONParserException(e);
        }
    }

    static int getNumberOfPagesFromSearch(String jsonString) throws JSONParserException {
        try {
            return new JSONObject(jsonString).getJSONObject("info").getInt("page");
        } catch (JSONException e) {
            throw new JSONParserException("Can't retrieve number of pages");
        }
    }
}
