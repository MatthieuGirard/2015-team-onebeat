package ch.epfl.sweng.team_onebeat;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import ch.epfl.sweng.team_onebeat.Exceptions.BuildableException;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.PendingData;
import ch.epfl.sweng.team_onebeat.FrontEnd.Network.SpotifyDataProvider;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.BooleanData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.MusicData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.MusicInformationData;
import ch.epfl.sweng.team_onebeat.FrontEnd.RetrieveData.SpotifyUser;

import static junit.framework.Assert.fail;

/**
 * Created by hugo on 20.11.15.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SpotifyDataProviderTest {

    private SpotifyDataProvider spotifyDataProvider = new SpotifyDataProvider();


    @Test
    public void connectionTest() {

        PendingData<SpotifyUser> data = spotifyDataProvider.connect("someone", "pass");

        data.blockAtMost(2000); // block at most 2s or get before if data is ready

        if(data.downloadState() != PendingData.DownloadState.LOADED){
            fail("data take more than 2s to be loaded");
        }


        try {
            data.get();
        } catch (BuildableException e) {
            fail("data should be accessible, look the parser");
        }

    }


    @Test
    public void existUserTest() {

        PendingData<BooleanData> data = spotifyDataProvider.existUser("someone", "pass");

        data.blockAtMost(2000); // block at most 2s or get before if data is ready

        if(data.downloadState() != PendingData.DownloadState.LOADED){
            fail("data take more than 2s to be loaded");
        }


        try {
            data.get();
        } catch (BuildableException e) {
            fail("data should be accessible, look the parser");
        }

    }




    @Test
    public void musicDataTest() {

        PendingData<MusicData> data = spotifyDataProvider.music("music");

        data.blockAtMost(2000); // block at most 2s or get before if data is ready

        if(data.downloadState() != PendingData.DownloadState.LOADED){
            fail("data take more than 2s to be loaded");
        }


        try {
            data.get();
        } catch (BuildableException e) {
            fail("data should be accessible, look the parser");
        }
    }



    @Test
    public void MusicInformationTest() {

        PendingData<MusicInformationData> data = spotifyDataProvider.musicInformation("key");

        data.blockAtMost(2000); // block at most 2s or get before if data is ready

        if(data.downloadState() != PendingData.DownloadState.LOADED){
            fail("data take more than 2s to be loaded");
        }


        try {
            data.get();
        } catch (BuildableException e) {
            fail("data should be accessible, look the parser");
        }
    }


    @Test
    public void searchMusicTest() {

        PendingData<List<MusicInformationData>> data = spotifyDataProvider.searchMusics("name");

        data.blockAtMost(2000); // block at most 2s or get before if data is ready

        if(data.downloadState() != PendingData.DownloadState.LOADED){
            fail("data take more than 2s to be loaded");
        }


        try {
            data.get();
        } catch (BuildableException e) {
            fail("data should be accessible, look the parser");
        }
    }





}






