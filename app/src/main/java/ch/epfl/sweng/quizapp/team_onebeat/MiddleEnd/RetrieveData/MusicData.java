package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate a music comming from the backend.
 *
 * how :
 * with a backendTask
 *
 *
 */

public class MusicData {





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
        public String toString() {
            return "MusicData";
        }

    }


}
