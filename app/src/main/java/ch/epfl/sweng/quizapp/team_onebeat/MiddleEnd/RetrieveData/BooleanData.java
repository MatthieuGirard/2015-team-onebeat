package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData;



/**
 * Created by hugo on 25.10.2015.
 *
 * encapsulate a boolean to an initial state
 * when the "DownloadedData" go in  Loaded State
 * the data notify observers.
 */
public class BooleanData extends DownloadedData implements  Copy<BooleanData>{


    private boolean value;

    public BooleanData(boolean value){
        this.value = value;
    }

    public void copy(BooleanData that){
        this.value = that.value;
    }

    public boolean value(){
        return value;
    }

}
