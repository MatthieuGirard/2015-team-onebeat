package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate all rooms subscribed by the user
 * backendTask : obtains id for room and construct a Set of DownloadData<Room>
 *
 * information :
 * Set<DownloadData<RoomData>> rooms;
 *
 * DownloadData :
 * decorate data and provide isLoaded(); method to indicate when the data can be provide.
 * see Network.DownloadData class for more info
 *
 */


public class RelatedRoomsData implements RetrieveData {


}
