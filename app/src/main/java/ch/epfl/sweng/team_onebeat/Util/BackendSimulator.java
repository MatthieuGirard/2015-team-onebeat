package ch.epfl.sweng.team_onebeat.Util;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.sweng.team_onebeat.FrontEnd.Network.Message;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.MessageFactory;

/**
 * Created by hugo on 07.11.15.
 */
public class BackendSimulator {



    private Map<Message, Message> response = new HashMap<>();



    public BackendSimulator(Map<Message, Message> response) throws JSONException {

        this.response = response;

    }



    public Message request(Message request, int delay ) throws InterruptedException {

        Message answer =  response.get(request);
        Thread.sleep(delay); // TODO : wait
        return answer;
    }


    public static class Factory {



        private Message backend_boolTrue = MessageFactory.Backend.bool(true);

        private Message backend_boolFalse = MessageFactory.Backend.bool(false);


        private Message frontend_existUser = MessageFactory.Frontend.existUser(1234);

        private Message frontend_subscribe = MessageFactory.Frontend.subscribe(1234, "hugo");

        private Message frontend_connect = MessageFactory.Frontend.connect(1234);


        public Factory() throws JSONException{

        }



        public BackendSimulator backend_firstConnection() throws JSONException {

            Map<Message, Message> backendResponse = new HashMap<>();

            backendResponse.put(frontend_existUser, backend_boolFalse);

            backendResponse.put(frontend_subscribe, backend_boolTrue);

            backendResponse.put(frontend_connect, backend_boolTrue); // TODO : need to be modified

            return new BackendSimulator(backendResponse);

        }




    }






}
