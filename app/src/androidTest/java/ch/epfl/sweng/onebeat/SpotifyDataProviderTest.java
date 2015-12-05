package ch.epfl.sweng.onebeat;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import ch.epfl.sweng.onebeat.exception.BuildableException;
import ch.epfl.sweng.onebeat.frontEnd.network.PendingData;
import ch.epfl.sweng.onebeat.frontEnd.network.SpotifyBackendInterface;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;

import static junit.framework.Assert.fail;

/**
 * Created by hugo on 20.11.15.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SpotifyDataProviderTest {


    @Test
    public void getMusicList() throws IOException {

        PendingData<List<Music>> pData =
                SpotifyBackendInterface.getInstance().getMusicList("proleter");

        pData.blockAtMost(2000); // block at most 2s or get before if data is ready

        if(pData.downloadState() != PendingData.DownloadState.LOADED){
            fail("pendingData after 2s : " + pData.downloadState());
        }


        try {
            pData.get();
        } catch (BuildableException e) {
            fail("data should be accessible, look the parser");
        }

    }




}






