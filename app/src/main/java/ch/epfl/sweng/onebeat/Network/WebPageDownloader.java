package ch.epfl.sweng.onebeat.Network;

import org.json.JSONException;

import ch.epfl.sweng.onebeat.Exceptions.JSONParserException;

/**
 * Created by Matthieu on 13.11.2015.
 */
public interface WebPageDownloader {

    void onPageDataRetrieved(String result) throws JSONException, JSONParserException;
}
