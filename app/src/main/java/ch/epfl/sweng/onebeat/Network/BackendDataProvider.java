package ch.epfl.sweng.onebeat.Network;

import org.json.JSONObject;

import ch.epfl.sweng.onebeat.Parsers.CreateRoomParser;

/**
 * Created by Matt
 */

public class BackendDataProvider extends DataProvider {

    public BackendDataProvider(DataProviderObserver callingActivity) {
        super(callingActivity);
    }

    public void createRoom(JSONObject jsonToSend) {
        super.setParser(new CreateRoomParser());
        super.setRequestType(RequestTypes.CREATE_ROOM);
        new SendDataTask(this).execute("http://onebeat.pythonanywhere.com/createRoom/", jsonToSend.toString());
    }
}



