package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate room subscribed by the user
 * backendTask : obtains id for room and construct a Set of RetrieveBuildableData<Room>
 *
 * information :
 * Set<RetrieveBuildableData<RoomData>> rooms;
 *
 */


public class RelatedRoomsData {

    public static class Builder extends RetrieveBuildableData<BooleanData> {

        @Override
        public void loadData(BooleanData that) {
            // TODO
        }

        @Override
        public BooleanData build() throws BuildableException {
            // TODO
            return null;
        }


        @Override
        public String toString(){
            return "RelatedRoomData";
        }

    }

}
