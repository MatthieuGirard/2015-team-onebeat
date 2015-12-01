package ch.epfl.sweng.onebeat.Network;

import org.json.JSONException;

import ch.epfl.sweng.onebeat.Exceptions.JSONParserException;
import ch.epfl.sweng.onebeat.Exceptions.ParseException;
import ch.epfl.sweng.onebeat.Exceptions.ParserNotDefinedException;

/**
 * Created by Matthieu on 13.11.2015.
 */
public interface WebPageDownloader {

    void onWebDataReception(String result) throws ParseException, ParserNotDefinedException;
}
