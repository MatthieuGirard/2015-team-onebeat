package ch.epfl.sweng.onebeat;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


import ch.epfl.sweng.onebeat.exception.BuildableException;
import ch.epfl.sweng.onebeat.exception.ParseException;
import ch.epfl.sweng.onebeat.frontEnd.network.BackendDownloader;
import ch.epfl.sweng.onebeat.frontEnd.network.HttpUriRequestFactory;
import ch.epfl.sweng.onebeat.frontEnd.network.PendingData;
import ch.epfl.sweng.onebeat.frontEnd.parser.Parser;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * Created by hugo on 20.11.15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PendingDataTest {


    @Test
    public void basicTest() throws IOException {

            HttpUriRequest request =
                    HttpUriRequestFactory
                            .getRequest("https://www.googleapis.com/freebase/v1/text/en/bob_dylan");


            // data encapsulate
            PendingData<Result> data = new PendingData<>();

            BackendDownloader<Result> ddl = new BackendDownloader<>(request, new MyParser());

            ddl.execute(data);

            // block at most 2s or get before if data is ready
            data.blockAtMost(2000);


            if(data.downloadState() != PendingData.DownloadState.LOADED){
                fail("after 2 s data in downloadState = "+ data.downloadState()  );
            }

            try {
                Log.d("#pendingDataTest", data.get().value);
            } catch (BuildableException e) {
                fail("data should be accessible");
            }



    }




    @Test
    public void observableTest() throws IOException {

        BoolSetTrueByUpdate observer =  new BoolSetTrueByUpdate();

        HttpUriRequest request =
                HttpUriRequestFactory
                        .getRequest("https://www.googleapis.com/freebase/v1/text/en/bob_dylan");


        // data encapsulate

        PendingData<Result> data = new PendingData<>();
        data.addObserver(observer);
        BackendDownloader<Result> ddl = new BackendDownloader<>(request, new MyParser());

        ddl.execute(data);


        // infinite loop if test fail
        while(observer.bool == false);




        try {
            Log.d("#pendingDataTest", data.get().value);
        } catch (BuildableException e) {
            fail("data should be accessible");
        }



    }






    @Test
    public void observable2Test() {
        BoolSetTrueByUpdate obs = new BoolSetTrueByUpdate();
        PendingData<Boolean> pData = new PendingData<>();
        pData.setData(new Boolean(true));
        pData.setDownloadState(PendingData.DownloadState.LOADED);

        pData.addObserver(obs);

        assertEquals(
                "pendingData need to notifyObs when obs subscribe after the pending data is loaded",
                true,
                obs.bool);

    }











    public class BoolSetTrueByUpdate implements Observer {

        public boolean bool = false;

        @Override
        public void update(Observable observable, Object data) {
            bool = true;
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
