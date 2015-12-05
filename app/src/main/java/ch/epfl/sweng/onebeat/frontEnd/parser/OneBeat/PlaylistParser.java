package ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Playlist;


/**
 * Created by hugo on 27.11.15.
 */
public class PlaylistParser implements Parser<Playlist> {


    private List<String> extractStringArray(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        for( int i = 0; i<array.length(); i++){
            list.add(array.getString(i));
        }
        return list;
    }

    @Override
    public Playlist parse(JSONObject obj) throws ParseException, BackendCommunicationException {

        try {

            String id = obj.getString("id");
            String creator = obj.getString("creator");
            String name = obj.getString("name");
            List<String> playlistId = extractStringArray(obj.getJSONArray("playlist"));
            List<String> participantsId = extractStringArray(obj.getJSONArray("members"));
            List<String> musicsAddedBy = extractStringArray(obj.getJSONArray("addedBy"));

            return new Playlist(id, name, playlistId, musicsAddedBy, creator, participantsId);



        } catch (JSONException e) {
            throw new ParseException();
        }



    }







}
