package ch.epfl.sweng.onebeat.Parsers;

import org.json.JSONException;
import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.RetrievedData.MusicInformationData;

/**
 * Created by hugo on 27.11.15.
 */


public class MusicInformationParser implements Parser<MusicInformationData>{


	@Override
	public MusicInformationData parse(String JSONStringToParse) throws ParseException {
		return null;
	}

	@Override
	public MusicInformationData parse(JSONObject obj) throws ParseException {


		try {

			int id = obj.getInt("id");
			String title = obj.getString("title");
			float duration = (float)obj.getDouble("duration");
			String spotifyRef = obj.getString("spotifyRef");
			int addedBy = obj.getInt("addedBy");
			String artist = obj.getString("artist");


			return new MusicInformationData(id,artist,title,duration,spotifyRef,addedBy);


		} catch (JSONException e) {
			throw new ParseException();
		}


	}



}
