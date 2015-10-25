package ch.epfl.sweng.quizapp.team_onebeat.Util;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network.Message;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network.Request;

/**
 * Created by hugo on 24.10.2015.
 *
 * backend simulator answer with delay on a request.
 * TODO : It's a class used for testing, so it's may be on the wrong place ?
 */
public final class BackEndSimulator extends Thread {


    Map<Request,Message> RequestToResponse = new HashMap<>();

    public BackEndSimulator(){
        // TODO : complete RequestToResponse with the followed TODO
    }

    // TODO : static factory Message (response) used in constructor to make the map request -> answer
    // TODO : complete in Middleend.Network.Request the request serviced by your endhost (some already implemented)
    // TODO : a get(Request) => Message with different waiting time process on a call


}
