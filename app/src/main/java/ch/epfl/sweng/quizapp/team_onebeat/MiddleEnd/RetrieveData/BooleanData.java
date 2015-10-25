package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;


import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.BuildableException;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate a boolean comming from the backend.
 *
 * how :
 * with a backendTask
 */
public class BooleanData {


    private boolean value;

    public BooleanData(boolean value){
        this.value = value;
    }

    public boolean value(){
        return value;
    }

    public static class Builder implements RetrieveBuildableData<BooleanData>{


        @Override
        public void copy(BooleanData that) {

        }

        @Override
        public boolean isLoaded() {
            return false;
        }

        @Override
        public boolean isBuildable() {
            return false;
        }

        @Override
        public BooleanData build() throws BuildableException{
            if(!isBuildable()) throw new BuildableException();
            return null;
        }

        @Override
        public String toString(){
            return "BooleanData";
        }


    }


}
