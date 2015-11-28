package ch.epfl.sweng.team_onebeat.FrontEnd.Controler;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observer;

import ch.epfl.sweng.team_onebeat.Exceptions.BackendCommunicationException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.DefaultNetworkProvider;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.Message;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.NetworkProvider;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.PendingData;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.Parser;

/**
 * Created by hugo on 08.11.15.
 */
public abstract class Controler implements Observer {



    public enum BackendService {ONE_BEAT_CONNECTION, SPOTIFY_CONNECTION}

    private final Map<BackendService, URL> urlForService = new HashMap<>();

    private static final NetworkProvider networkProvider = new DefaultNetworkProvider();


    private static LinkedList<Message> requestTracker = new LinkedList<>();



    public Controler()  {


        try {
            urlForService.put(BackendService.ONE_BEAT_CONNECTION, new URL("serverForConnection.com"));
            urlForService.put(BackendService.SPOTIFY_CONNECTION, new URL("spotify.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }



    public LinkedList<Message> requestTracker(){
        return new LinkedList<>(requestTracker);
    }



    public <T> PendingData<T> pendingData(BackendService backendService, Message request, Parser<T> parser)
            throws BackendCommunicationException {

        requestTracker.push(request);

        return new PendingData<>(
                urlForService.get(backendService),
                networkProvider,
                request,
                parser
                );

    }




}
