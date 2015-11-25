package ch.epfl.sweng.onebeat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthieu on 16.11.2015.
 */
public class JSONParser {

    static public List<Song> parseFromSearchAPI(String jsonStringToParse) throws JSONParserException {

        try {
            JSONObject json = new JSONObject(jsonStringToParse);
            JSONObject info = json.getJSONObject("info");

            int numResults = info.getInt("num_results");
            int numPages = info.getInt("page");

            final int limit = 7;

            JSONArray jsonTracks = json.optJSONArray("tracks");

            List<Song> tracksFound = new ArrayList<>();

            for (int i = 0; i < Math.min(jsonTracks.length(), limit); i++) {
                JSONObject actualTrack = jsonTracks.getJSONObject(i);

                String artist = actualTrack.getJSONArray("artists").getJSONObject(0).getString("name");
                String songName = actualTrack.getString("name");
                String spotifyRef = actualTrack.getString("href");
                String duration = "";

                tracksFound.add(new Song(songName, artist, duration, spotifyRef));

            }
            return tracksFound;
        } catch (JSONException e) {
            throw new JSONParserException(e);
        }
    }

    static public void parseFromUserJSON(String jsonString) throws JSONParserException {
        try {
            JSONObject baseJSON = new JSONObject(jsonString);
            String name = baseJSON.getString("display_name");
            String spotifyID = baseJSON.getString("id");

            SpotifyUser.getInstance().setInfos(name, spotifyID); }
        catch (JSONException e) {
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
