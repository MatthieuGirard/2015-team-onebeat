package ch.epfl.sweng.onebeat.frontEnd.parser.Spotify;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;

/**
 * Created by Matthieu on 01.12.2015.
 */
public class SearchMusicParser implements Parser<List<Music>> {



    @Override
    public List<Music> parse(JSONObject obj) throws ParseException, BackendCommunicationException {
        try {

            JSONObject info = obj.getJSONObject("info");

            int numResults = info.getInt("num_results");
            int numPages = info.getInt("page");

            final int limit = 7;

            JSONArray jsonTracks = obj.optJSONArray("tracks");

            List<Music> tracksFound = new ArrayList<>();

            for (int i = 0; i < Math.min(jsonTracks.length(), limit); i++) {
                JSONObject actualTrack = jsonTracks.getJSONObject(i);

                String artist = actualTrack.getJSONArray("artists").getJSONObject(0).getString("name");
                String songName = actualTrack.getString("name");
                String spotifyRef = actualTrack.getString("href");
                String duration = "";

                tracksFound.add(new Music(songName, artist, 0.0f, spotifyRef));

            }
            return tracksFound;
        } catch (JSONException e) {
            throw new ParseException();
        }
    }





}
