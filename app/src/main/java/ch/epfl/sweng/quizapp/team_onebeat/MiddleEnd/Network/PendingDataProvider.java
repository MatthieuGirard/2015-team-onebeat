package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;


import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.Parser;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.RetrieveData;

/**
 * Created by hugo on 24.10.2015.
 *
 * aim :
 * get an instance of T when dealing with the backend.
 *
 * use case:
 * PendingDataProvider taking a server url and a networkProvider
 * and provide the asked pendingData on request.
 * Pending data are buildable when isloaded() is true.
 * In this way Controler can ask from data without waiting.
 *
 *
 */


// TODO : possible to have just have : <T extends RetrieveBuildableData<E>> ?

public final class PendingDataProvider<T extends RetrieveData>  {

    private String serverUrl;
    private NetworkProvider networkProvider = null;


    public PendingDataProvider(String serverUrl, NetworkProvider networkProvider){
    this.serverUrl = serverUrl;
    this.networkProvider = networkProvider;
    }



    public <T> PendingData<T> backendTask(Request request, Parser<T> parser){

        return new PendingData<>(serverUrl, networkProvider, request, parser);

    }


}
