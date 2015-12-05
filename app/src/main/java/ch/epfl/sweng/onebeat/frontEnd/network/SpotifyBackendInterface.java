package ch.epfl.sweng.onebeat.frontEnd.network;

import android.util.Log;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;
import java.util.List;

import ch.epfl.sweng.onebeat.exception.BuildableException;
import ch.epfl.sweng.onebeat.frontEnd.parser.Spotify.SearchMusicParser;
import ch.epfl.sweng.onebeat.frontEnd.parser.Spotify.UserParser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.User;


/**
 * Created by hugo on 20.11.15.
 */
public class SpotifyBackendInterface  implements PlayerNotificationCallback, ConnectionStateCallback {


    private static SpotifyBackendInterface provider = new SpotifyBackendInterface();


    private boolean isUserLogged = false;

    private Config playerConfig = null;

    private Player player = null;

    private User user = null;




    private SpotifyBackendInterface(){

        if(provider != null){
            throw new IllegalStateException("singleton already instanciate");
        }

    }


    public static SpotifyBackendInterface getInstance(){
        return provider;
    }




    // initialize and wait for logged callback
    public void initialize( Config config ) {

        Log.d("#Spot", "initialize");

        Player.InitializationObserver initObs = new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                player.addConnectionStateCallback(SpotifyBackendInterface.getInstance());
                player.addPlayerNotificationCallback(SpotifyBackendInterface.getInstance());
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
            }
        };


        this.player = Spotify.getPlayer(config, this, initObs);
        this.playerConfig = config;


    }






    @Override
    public void onLoggedIn() {


        Log.d("#SpotifyBackendInterface", "onLoggedIn");


        try {
        // make request to find the spotify id and the spotify name of the client

        String stringUrl = "https://api.spotify.com/v1/me";

        HttpUriRequest request = HttpUriRequestFactory.getRequest(stringUrl);

        request.setHeader("Authorization", "Bearer " + this.playerConfig.oauthToken);

        while(this.user == null) {
            // download the result in the pending data

            PendingData<User> pendingData = new PendingData<>();

            BackendDownloader<User> ddl = new BackendDownloader<User>(request, new UserParser());

            ddl.execute(pendingData);


            // block until ready : need the spotify interface to continue (TODO subcribe to the pData ? )

            pendingData.blockAtMost(1000);

            if (pendingData.downloadState() == PendingData.DownloadState.LOADED) {


                    this.user = pendingData.get();
                    isUserLogged = true;

            }
        }

        } catch (BuildableException | IOException e) {

        }



    }


    @Override
    public void onLoggedOut() {}

    @Override
    public void onLoginFailed(Throwable error){}

    @Override
    public void onTemporaryError() {}

    @Override
    public void onConnectionMessage(String message) { }

    @Override
    public void onPlaybackEvent(PlayerNotificationCallback.EventType eventType, PlayerState playerState) { }

    @Override
    public void onPlaybackError(PlayerNotificationCallback.ErrorType errorType, String errorDetails) { }









    public boolean isUserLogged(){
        return isUserLogged;
    }



    public String getPlayerRef(){
        return user.getId();
    }


    public void playMusic(String musicId){
        player.play(musicId);
    }

    public void pauseMusic(){
        player.pause();
    }

    public void setMusicAt(int mSeconds){
        player.seekToPosition(mSeconds);
    }




    public PendingData<List<Music>> getMusicList(String search) throws IOException {

        PendingData<List<Music>> pendingData = new PendingData<>();
        String stringUrl = "http://ws.spotify.com/search/1/track.json?q=" + search.replace(" ", "%20");

        HttpUriRequest request =  HttpUriRequestFactory.getRequest(stringUrl);


        BackendDownloader<List<Music>> ddl = new BackendDownloader<>( request, new SearchMusicParser());

        ddl.execute(pendingData);

        return pendingData;
    }






}
