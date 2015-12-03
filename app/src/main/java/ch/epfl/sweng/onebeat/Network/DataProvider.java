package ch.epfl.sweng.onebeat.Network;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import ch.epfl.sweng.onebeat.Activities.MainActivity;
import ch.epfl.sweng.onebeat.Activities.RoomActivity;
import ch.epfl.sweng.onebeat.Activities.SelectRoomActivity;
import ch.epfl.sweng.onebeat.Exceptions.NotDefinedUserInfosException;
import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Exceptions.ParserNotDefinedException;
import ch.epfl.sweng.onebeat.Parsers.Parser;
import ch.epfl.sweng.onebeat.R;
import ch.epfl.sweng.onebeat.RetrievedData.Room;
import ch.epfl.sweng.onebeat.RetrievedData.Song;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;

/**
 * Created by Matthieu on 02.12.2015.
 */
public abstract class DataProvider {

    public final String serverURL = "http://onebeat.pythonanywhere.com/";

    private Context callingActivity;
    private Parser parser = null;
    private RequestTypes requestType = RequestTypes.CREATE_ROOM;

    public enum RequestTypes {GET_SPOTIFY_USER, GET_LIST_OF_SPOTIFY_SONGS, CREATE_ROOM, GET_LIST_OF_ROOMS, GET_ROOM_INFOS, ADD_USER };

    public DataProvider(Context callingActivity) {
        this.callingActivity = callingActivity;
    }

    public void onWebDataReception(String result) throws ParseException, ParserNotDefinedException, NotDefinedUserInfosException, JSONException {
        if (parser != null) {
            Object parsedResult = parser.parse(result);
            switch (requestType) {
                case CREATE_ROOM:
                    JSONObject response = (JSONObject) parsedResult;
                    boolean hasCreated = response.getBoolean("added");
                    SelectRoomActivity selectRoomActivity = (SelectRoomActivity) callingActivity;
                    if (hasCreated) {
                        int roomID = response.getInt("id");
                        selectRoomActivity.onNewRoomMessage(roomID);
                    }
                    else {
                        String error = response.getString("error");
                        selectRoomActivity.errorOnCreatingRoom(error);
                    }
                    break;
                case GET_SPOTIFY_USER:
                    ((MainActivity) callingActivity).onUserLogged();
                    break;

                case GET_LIST_OF_SPOTIFY_SONGS:
                    ((RoomActivity) callingActivity).setListOfSongsFromSearch((List<Song>) parsedResult);
                    break;

                case GET_LIST_OF_ROOMS:
                    ((SelectRoomActivity) callingActivity).setListOfRooms((List<Room>) parsedResult);
                    break;
                case GET_ROOM_INFOS:
                    ((RoomActivity) callingActivity).setRoomInformations((Room) parsedResult);
                    break;

                case ADD_USER:
                    ((MainActivity) callingActivity).onUserRegistered();
            }
        } else {
            throw new ParserNotDefinedException();
        }
    }

    public void setParser(Parser parser) { this.parser = parser; }
    public void setRequestType(RequestTypes requestType) { this.requestType = requestType; }
}
