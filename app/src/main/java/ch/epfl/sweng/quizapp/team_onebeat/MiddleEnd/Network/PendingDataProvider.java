package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;


import android.os.AsyncTask;

import ch.epfl.sweng.quizapp.team_onebeat.Exceptions.NotImplementedException;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.Parser;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.PendingData;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.RetrieveData;

/**
 * Created by hugo on 24.10.2015.
 *
 * aim :
 * get an instance of T when dealing with the backend.
 *
 * use case:
 * BackendTask constructor take a RetrieveBuildable<T> :
 * RetrieveBuildable<T> has an isLoaded() method to provide information :
 * does the backend has loaded the builder ?
 * RetrieveBuildable<T> has a build() method that return a T instance
 * In this way GUI can send a builder in backendTask constructor
 * and wait until isloaded() is true before build the instance.
 *
 * how it work :
 * BackendTask retrieve data from the backend on a JSONOBJECT (async) with a request.
 * A parser translate JSON in a representative instance of T
 * and then, it indicate the value to "loadInBuilder" passed in constructor and set the
 * isLoaded to true so that the GUI is ok to display instance.
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
