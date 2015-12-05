package ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.exception.BackendCommunicationException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;

/**
 * Created by hugo on 27.11.15.
 */


public class MusicParser implements Parser<Music> {


	@Override
	public Music parse(JSONObject obj) throws ParseException, BackendCommunicationException {

		try {

			//int id = obj.getInt("id");
			String title = obj.getString("title");
			float duration = (float)obj.getDouble("duration");
			String spotifyRef = obj.getString("spotifyRef");
			//int addedBy = obj.getInt("addedBy");
			String artist = obj.getString("artist");


			return new Music(title,artist, duration,spotifyRef);


		} catch (JSONException e) {
			throw new ParseException();
		}


	}



}
