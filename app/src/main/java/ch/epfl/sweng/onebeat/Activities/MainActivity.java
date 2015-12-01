package ch.epfl.sweng.onebeat.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ch.epfl.sweng.onebeat.Network.DataProviderObserver;
import ch.epfl.sweng.onebeat.Network.DownloadWebpageTask;
import ch.epfl.sweng.onebeat.Network.SpotifyDataProvider;
import ch.epfl.sweng.onebeat.Parsers.JSONParser;
import ch.epfl.sweng.onebeat.Exceptions.JSONParserException;
import ch.epfl.sweng.onebeat.Exceptions.NotDefinedUserInfosException;
import ch.epfl.sweng.onebeat.R;
import ch.epfl.sweng.onebeat.RetrievedData.SpotifyUser;
import ch.epfl.sweng.onebeat.Network.WebPageDownloader;

public class MainActivity extends AppCompatActivity implements ConnectionStateCallback, PlayerNotificationCallback, DataProviderObserver {

    private static final String CLIENT_ID = "eb868e0c8f33441b86de1904b3503f10";
    private static final String REDIRECT_URI = "onebeatapp://callback";

    private static final int REQUEST_CODE = 1337;

    public final static String EXTRA_MESSAGE = "ch.epfl.sweng.onebeat.CREATING_ROOM_MESSAGE";


    private Player mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

        FloatingActionButton FAB = (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RoomCreatorDialogFragment dialog = new RoomCreatorDialogFragment();
                dialog.show(getSupportFragmentManager(), "Room Creator");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {

                new SpotifyDataProvider(this).getUserInfos(response.getAccessToken());

/*                Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
                    @Override
                    public void onInitialized(Player player) {
                        mPlayer = player;
                        mPlayer.addConnectionStateCallback(MainActivity.this);
                        mPlayer.addPlayerNotificationCallback(MainActivity.this);
                        mPlayer.play("spotify:track:2TpxZ7JUBn3uw46aR7qd6V");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });*/
            }
        }
    }

/*   public void searchSong(View view) {

        Button goButton = (Button) findViewById(R.id.goButton);
        goButton.setEnabled(false);
        goButton.setText("Wait...");

        String searchInput = ((EditText) findViewById(R.id.searchField)).getText().toString();

        String stringUrl = "http://ws.spotify.com/search/1/track.json?q="+ searchInput.replace(" ", "%20");

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask(this).execute(stringUrl);
        }
    }*/

    @Override
    protected void onDestroy() {
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    @Override
    public void onLoggedIn() {

    }

    @Override
    public void onLoggedOut() {

    }

    @Override
    public void onLoginFailed(Throwable throwable) {

    }

    @Override
    public void onTemporaryError() {

    }

    @Override
    public void onConnectionMessage(String s) {

    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {

    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

    }

    @Override
    public void onDataReception(Object data) {

    }

    @SuppressLint("ValidFragment")
    private class RoomCreatorDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            final LayoutInflater inflater = getActivity().getLayoutInflater();

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            final View v = inflater.inflate(R.layout.dialog_create_room, null);
            builder.setView(v)
                    // Add action buttons
                    .setPositiveButton(R.string.partyOn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(RoomCreatorDialogFragment.this.getActivity(), RoomActivity.class);
                            EditText roomNameField = (EditText) v.findViewById(R.id.roomName);
                            EditText roomPasswordField = (EditText) v.findViewById(R.id.roomPassword);

                            JSONObject jsonToSend = new JSONObject();
                            try {
                                jsonToSend.put("creator", SpotifyUser.getInstance().getPseudo()); // TODO
                                jsonToSend.put("name", roomNameField.getText().toString());
                                jsonToSend.put("password", roomPasswordField.getText().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (NotDefinedUserInfosException e) {
                                e.printStackTrace();
                            }
                            //String message = roomNameField.getText().toString();
                            //intent.putExtra(EXTRA_MESSAGE, message);

                            excutePost("http://onebeat.pythonanywhere.com/createRoom", jsonToSend.toString());
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            RoomCreatorDialogFragment.this.getDialog().cancel();
                        }
                    });
            return builder.create();
        }
    }

    public static String excutePost(String targetURL, String dataToSend)
    {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            //Create urlConnection
            url = new URL(targetURL);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            //Send request
            DataOutputStream wr = new DataOutputStream (urlConnection.getOutputStream ());
            wr.writeBytes (dataToSend);
            wr.flush ();
            wr.close ();

            //Get Response
            InputStream is = urlConnection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {

            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
