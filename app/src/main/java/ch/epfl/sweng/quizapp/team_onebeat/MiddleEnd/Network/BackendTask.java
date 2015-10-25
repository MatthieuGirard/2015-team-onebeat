package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;


import android.os.AsyncTask;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.Parser;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.RetrieveBuildableData;

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

// TODO : possible to have just have : <T extends RetrieveBuildableData<E>>

public final class BackendTask<E,T extends RetrieveBuildableData<E>> extends AsyncTask<Request, Void, T> {

    private T loadInBuilder;
    private Parser<T> parser;
    private Request request;

    public BackendTask(Request request, Parser<T> parser, T loadInBuilder){

        this.parser = parser;
        this.loadInBuilder = loadInBuilder;
        this.request = request;

    }


    @Override
    protected T doInBackground(Request... request) {
        // reqest => JSON
        // JSON => Buildable<T>
        // loadInBuilder.copy(Buildable<T>)
        // setLoaded to true
        // TODO : how to throw BuildableException when parser fail or data corrupted ?

        return null;
    }


}
