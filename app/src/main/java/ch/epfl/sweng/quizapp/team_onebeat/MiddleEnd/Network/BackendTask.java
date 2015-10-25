package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;


import android.os.AsyncTask;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.Parser;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.Copy;

/**
 * Created by hugo on 24.10.2015.
 *
 * BackendTask can provide an instance of a "parametredData" in a default state with
 * the expectation that the instance will be parametrized in the future.
 *
 * BackendTask retrieve data from the backend on a JSONOBJECT (async) with a request.
 * A parser translate JSON in a representative instance.
 * "onPostExecute" method set the parametredData with the values of the representative instance.
 *
 */

public final class BackendTask<T extends Copy<T>> extends AsyncTask<Request, Void, T> {

    private T parametredData;
    private Parser<T> parser;
    private Request request;

    public BackendTask(Request request, Parser<T> parser, T parametredData){

        this.parser = parser;
        this.parametredData = parametredData;
        this.request = request;
    }

    public Request getRequest(){
        return request;
    }
    public T getParametredData(){
        return parametredData;
    }


    @Override
    protected T doInBackground(Request... request) {
        return null;
    }

    @Override
    public void onPostExecute( T buildedFromBackend ){
        parametredData.copy(buildedFromBackend);
    }

}
