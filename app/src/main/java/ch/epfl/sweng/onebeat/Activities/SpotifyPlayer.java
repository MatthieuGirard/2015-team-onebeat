package ch.epfl.sweng.onebeat.Activities;

import android.content.Context;
import android.util.Log;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;

import ch.epfl.sweng.onebeat.GeneralConstants;
import ch.epfl.sweng.onebeat.RetrievedData.Room;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;

/**
 * Created by M4ttou on 05.12.2015.
 */
public class SpotifyPlayer {

    private Context callingActivity;

    final private Config playerConfig;

    Player mPlayer;

    public SpotifyPlayer(Context callingActivity) {
        this.callingActivity = callingActivity;
        playerConfig = new Config(callingActivity, SpotifyUser.getInstance().getToken(), GeneralConstants.CLIENT_ID);
    }

    public void play(final String spotifyRef) {
        mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                mPlayer.play(spotifyRef);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("RoomActivity", "Could not initialize player: " + throwable.getMessage());
            }
        });
    }

    public void pause() {
        mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                mPlayer.pause();
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("RoomActivity", "Could not initialize player: " + throwable.getMessage());
            }
        });
    }
}
