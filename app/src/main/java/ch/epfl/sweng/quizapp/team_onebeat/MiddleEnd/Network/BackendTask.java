package ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Network;


import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import ch.epfl.sweng.quizapp.team_onebeat.MiddleEnd.Parser.Parser;

/**
 * Created by hugo on 24.10.2015.
 * BackendTask execute request on the backend
 * it can send request, retrieve the JSONObject, and parse it in a T class
 */

public final class BackendTask<T> extends AsyncTask<Request, Void, T>{

    private Parser<T> parser;

    public BackendTask( Parser<T> parser){
    this.parser = parser;
    }

    @Override
    protected T doInBackground(Request... request) {
        return null;
    }


}
