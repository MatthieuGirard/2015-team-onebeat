package ch.epfl.sweng.onebeat.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import org.json.JSONException;

import ch.epfl.sweng.onebeat.Exceptions.NotDefinedUserInfosException;
import ch.epfl.sweng.onebeat.GeneralConstants;
import ch.epfl.sweng.onebeat.Network.BackendDataProvider;
import ch.epfl.sweng.onebeat.Network.SpotifyDataProvider;
import ch.epfl.sweng.onebeat.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(GeneralConstants.CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                GeneralConstants.REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {

                new SpotifyDataProvider(this).getUserInfos(response.getAccessToken());
            }
        }
    }

    // When user is logged from Spotify api
    public void onUserLogged() throws NotDefinedUserInfosException, JSONException {
        new BackendDataProvider(this).addUser();
    }
    // When user is saved inside backend
    public void onUserRegistered() {
        Intent intent = new Intent(this, SelectRoomActivity.class);
        Log.d("KEINFO", "Launching Select room");
        startActivity(intent);
    }
}
