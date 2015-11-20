package ch.epfl.sweng.team_onebeat;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;

import ch.epfl.sweng.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.team_onebeat.Exceptions.ParseException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.DefaultNetworkProvider;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.PendingData;
import ch.epfl.sweng.team_onebeat.FrontEnd.Parser.Parser;

import static junit.framework.Assert.fail;

/**
 * Created by hugo on 20.11.15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PendingDataTest {

    @Test
    public void pendingDataTest() {

        try {


            // url with json
            URL url = new URL("https://www.googleapis.com/freebase/v1/text/en/bob_dylan");


            // data encapsulate
            PendingData<Result> data = new PendingData<Result>(
                    url,
                    new DefaultNetworkProvider(),
                    new MyParser());

            // block at most 2s or get before if data is ready
            data.blockAtMost(2000);


            if(data.downloadState() != PendingData.DownloadState.LOADED){
                fail("data take more than 2s to be loaded");
            }

            try {
                Log.d("#pendingDataTest", data.get().value);
            } catch (BuildableException e) {
                fail("data should be accessible, look the parser");
            }

        } catch (MalformedURLException e) {
            fail("wrong url");
        }


    }


    public static class Result {

        public String value;
        public Result(String value){
            this.value = value;
        }

    }

    public static class MyParser implements Parser<Result> {

        @Override
        public Result parse(JSONObject obj) throws ParseException {
            try {
                return new Result(obj.getString("result"));
            } catch (JSONException e) {
                throw new ParseException();
            }
        }

    }


}
