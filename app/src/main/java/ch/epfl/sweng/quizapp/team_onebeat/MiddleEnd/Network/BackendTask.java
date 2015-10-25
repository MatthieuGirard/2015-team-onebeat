package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;


import android.os.AsyncTask;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.Parser;
import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.RetrieveData.Buildable;

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

public final class BackendTask<T extends Buildable<T>> extends AsyncTask<Request, Void, T> {

    private Buildable<T> loadInBuilder;
    private Parser<Buildable<T>> parser;
    private Request request;

    public BackendTask(Request request, Parser<Buildable<T>> parser, Buildable<T> loadInBuilder){

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
        return null;
    }


}
