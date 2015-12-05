package ch.epfl.sweng.onebeat;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import ch.epfl.sweng.onebeat.exception.BadInterfaceRequestException;
import ch.epfl.sweng.onebeat.exception.BuildableException;
import ch.epfl.sweng.onebeat.frontEnd.activity.LoginActivity;
import ch.epfl.sweng.onebeat.frontEnd.network.OneBeatBackendInterface;
import ch.epfl.sweng.onebeat.frontEnd.network.PendingData;
import ch.epfl.sweng.onebeat.frontEnd.network.PendingData.DownloadState;
import ch.epfl.sweng.onebeat.frontEnd.network.SpotifyBackendInterface;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Playlist;

/**
 * Created by hugo on 01.12.15.
 */
public class OneBeatBackendInterfaceTest extends ActivityInstrumentationTestCase2<LoginActivity> {



    private final String testPlaylistId = "31";
    private final String spotifyId = "1132211247";

    public OneBeatBackendInterfaceTest(){
        super(LoginActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.getActivity().getApplication().onCreate();
        while (!SpotifyBackendInterface.getInstance().isUserLogged());
    }


    @Test
    public void testGetUser() {

        try {

            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .getUser()
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);


        } catch ( BadInterfaceRequestException e) {
            fail("interface fail to construct correct request");
        }


    }


    @Test
    public void testGetPlaylist(){

        try {

            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .getPlaylist(testPlaylistId)
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);

           PendingData<Playlist> pData = OneBeatBackendInterface.getInstance()
                    .getPlaylist(testPlaylistId)
                    .blockAtMost(1000);

            try {
                Log.d("musics", pData.get().getMusics().toString());
            } catch (BuildableException e) {
                e.printStackTrace();
            }


        } catch ( BadInterfaceRequestException e) {
            fail("interface fail to construct correct request");
        }


    }


    @Test
    public void testGetMusic()  {


        try {

            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .getMusic("5")
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);


        } catch ( BadInterfaceRequestException e) {
            fail("interface fail to construct correct request");
        }



    }


    @Test
    public void testGetSubcribedPlaylist() {


        try {

            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .getSubscribedPlaylist()
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);


        } catch ( BadInterfaceRequestException e) {
            fail("interface fail to construct correct request");
        }




    }



    @Test
    public void testAddUser() {


        try {

            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .addUser("docNoodle")
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);


        } catch ( BadInterfaceRequestException e ) {
            fail("interface fail to construct correct request");
        }


    }


    @Test
    public void testAddPlaylist() {

        String randomString = UUID.randomUUID().toString();
        try {

            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .addPlaylist(randomString, "pass1")
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);


        } catch ( BadInterfaceRequestException e ) {
            fail("interface fail to construct correct request");
        }


    }



    @Test
    public void testAddMusic() {


        try {

            Music music = new Music("title1", "artist1", 31.1f,SpotifyBackendInterface.getInstance().getPlayerRef());
            Playlist playlist = new Playlist(
                    testPlaylistId,
                    "name1",
                    new ArrayList(),
                    new ArrayList(),
                    SpotifyBackendInterface.getInstance().getPlayerRef(),
                    new ArrayList());


            boolean canLoad =
                    OneBeatBackendInterface.getInstance()
                            .addMusic(music, playlist)
                            .blockAtMost(1000)
                            .downloadStateIs(DownloadState.LOADED);

            assertEquals("can't load after 1s", true, canLoad);


        } catch ( BadInterfaceRequestException e ) {
            fail("interface fail to construct correct request");
        }


    }





}
