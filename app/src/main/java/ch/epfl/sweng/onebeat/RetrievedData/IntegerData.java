package ch.epfl.sweng.onebeat.RetrievedData;

/**
 * Created by hugo on 27.11.15.
 */
public class IntegerData {

    private int value;
    private boolean error = false;


    public IntegerData(int value){
        this.value = value;
    }

    public IntegerData setError(){
        error = true;
        return this;
    }

    public int getValue(){
        return value;
    }





}
