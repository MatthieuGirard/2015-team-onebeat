package ch.epfl.sweng.onebeat.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.PlaylistData;

/**
 * Created by hugo on 27.11.15.
 */
public class PlaylistParser implements Parser<PlaylistData> {



    @Override
    public PlaylistData parse(JSONObject obj) throws ParseException {

        try {

            int id = obj.getInt("id");

            int addedBy = obj.getInt("creator");
            String name = obj.getString("nameOfRoom");

            int lastUpdateDate = obj.getInt("lastUpdate");
            List<Integer> participants = new ArrayList<>();
            List<Integer> musics = new ArrayList<>();

            JSONArray jsonArray = obj.getJSONArray("tracksIDs");



            for( int i = 0; i < jsonArray.length(); i++){

                musics.add(jsonArray.getInt(i));

            }

            jsonArray = obj.getJSONArray("participants");

            for(int i = 0; i<jsonArray.length(); i++){

                participants.add(jsonArray.getInt(i));

            }



            return new PlaylistData(id, name, musics,addedBy,  participants,  lastUpdateDate);



        } catch (JSONException e) {
            throw new ParseException();
        }



    }







}
