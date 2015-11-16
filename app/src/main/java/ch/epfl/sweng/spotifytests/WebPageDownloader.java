package ch.epfl.sweng.spotifytests;

import org.json.JSONException;

/**
 * Created by Matthieu on 13.11.2015.
 */
public interface WebPageDownloader {

    void onPageDataRetrieved(String result) throws JSONException, JSONParserException;
}
