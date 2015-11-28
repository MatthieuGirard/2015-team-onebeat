package ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hugo on 25.10.2015.
 *
 * use case :
 * encapsulate a music coming from the backend.
 *
 *
 *
 */


public class MusicData  {


    // TODO : update type
    private List<Byte> data;


    public MusicData(List<Byte> data){
        this.data = data;
    }


    public List<Byte> getData(){
        return new ArrayList<>(data);
    }



}
