package ch.epfl.sweng.onebeat.frontEnd.network;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.sweng.onebeat.exception.BadInterfaceRequestException;
import ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat.MusicParser;
import ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat.PlaylistParser;
import ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat.PostAddedToBooleanParser;
import ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat.SubscribedPlaylistParser;
import ch.epfl.sweng.onebeat.frontEnd.parser.OneBeat.UserParser;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Music;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.Playlist;
import ch.epfl.sweng.onebeat.frontEnd.retrieveData.User;

/**
 * Created by hugo on 07.11.15.
 */

public class OneBeatBackendInterface {



    private static final OneBeatBackendInterface provider = new OneBeatBackendInterface();



    private OneBeatBackendInterface(){

        if(provider != null){
            throw new IllegalStateException("singleton already instanciate");
        }

    }


    public static OneBeatBackendInterface getInstance() {
        return provider;
    }






    public PendingData<Boolean> addPlaylist(String name, String pass)
            throws BadInterfaceRequestException {

        PendingData<Boolean> pendingData = new PendingData<>();

        try {


            JSONObject json = new JSONObject();
            json.put("creator", "" + SpotifyBackendInterface.getInstance().getPlayerRef());
            json.put("name", name);
            json.put("password", pass);

            Map<String,String> namesValues = new HashMap<>();
            namesValues.put("request", json.toString());


            HttpUriRequest request = HttpUriRequestFactory
                    .postRequest("http://onebeat.pythonanywhere.com/createRoom/", namesValues);



            BackendDownloader<Boolean> ddl =
                    new BackendDownloader<>(request, new PostAddedToBooleanParser());

            ddl.execute(pendingData);

        } catch (IOException | JSONException e) {
            throw new BadInterfaceRequestException();
        }


        return pendingData;
    }






    public PendingData<Boolean> addUser(String pseudo) throws BadInterfaceRequestException {

        PendingData<Boolean> pendingData = new PendingData<>();

        try {


            JSONObject json = new JSONObject()
                    .put("id", SpotifyBackendInterface.getInstance().getPlayerRef())
                    .put("name", pseudo);


            Map<String,String> namesValues = new HashMap<>();
            namesValues.put("request", json.toString());


            HttpUriRequest request = HttpUriRequestFactory.postRequest(
                    "http://onebeat.pythonanywhere.com/addUser/",
                    namesValues);


            BackendDownloader<Boolean> ddl =
                    new BackendDownloader<>(request, new PostAddedToBooleanParser());

            ddl.execute(pendingData);

        } catch (IOException | JSONException e) {
            throw new BadInterfaceRequestException();
        }


        return pendingData;

    }




    public PendingData<Boolean> addMusic(Music music, Playlist playlist)
            throws BadInterfaceRequestException {

        PendingData<Boolean> pendingData = new PendingData<>();

        try {


            JSONObject json = new JSONObject()
                    .put("room", playlist.getId())
                    .put("artist", music.getArtist())
                    .put("title", music.getTitle())
                    .put("duration", music.getDuration())
                    .put("spotifyRef", music.getSpotifyRef())
                    .put("addedBy", SpotifyBackendInterface.getInstance().getPlayerRef() );

            Map<String,String> namesValues = new HashMap<>();
            namesValues.put("request", json.toString());


            HttpUriRequest request = HttpUriRequestFactory.postRequest(
                    "http://onebeat.pythonanywhere.com/addSong/",
                    namesValues);



            BackendDownloader<Boolean> ddl =
                    new BackendDownloader<>(request, new PostAddedToBooleanParser());

            ddl.execute(pendingData);

        } catch (IOException | JSONException e) {
            throw new BadInterfaceRequestException();
        }


        return pendingData;

    }





    public PendingData<User> getUser() throws BadInterfaceRequestException {



        PendingData<User> pendingData = new PendingData<>();

        try {

            String id = SpotifyBackendInterface.getInstance().getPlayerRef();
            HttpUriRequest request= HttpUriRequestFactory
                    .getRequest("http://onebeat.pythonanywhere.com/getUser?id=" + id);



            BackendDownloader<User> ddl = new BackendDownloader<>(request, new UserParser());

            ddl.execute(pendingData);

        } catch ( IOException  e) {
            e.printStackTrace();
            throw new BadInterfaceRequestException();
        }


        return pendingData;

    }





    public PendingData<Music> getMusic(String idMusic) throws BadInterfaceRequestException {



        PendingData<Music> pendingData = new PendingData<>();
        try {

            HttpUriRequest request= HttpUriRequestFactory
                    .getRequest("http://onebeat.pythonanywhere.com/getSong?id="+idMusic);


            BackendDownloader<Music> ddl = new BackendDownloader<>(request, new MusicParser());

            ddl.execute(pendingData);

        } catch ( IOException  e) {
            e.printStackTrace();
            throw new BadInterfaceRequestException();
        }


        return pendingData;

    }




    public PendingData<Playlist> getPlaylist(String idPlaylist) throws BadInterfaceRequestException {



        PendingData<Playlist> pendingData = new PendingData<>();

        try {

            HttpUriRequest request= HttpUriRequestFactory
                    .getRequest("http://onebeat.pythonanywhere.com/getRoom?id=" + idPlaylist);


            BackendDownloader<Playlist> ddl =
                    new BackendDownloader<>(request, new PlaylistParser());

            ddl.execute(pendingData);

        } catch ( IOException  e) {
            e.printStackTrace();
            throw new BadInterfaceRequestException();
        }


        return pendingData;

    }





    public PendingData<List<String>> getSubscribedPlaylist() throws BadInterfaceRequestException {


        PendingData<List<String>> pendingData = new PendingData<>();

        try {

            String id = SpotifyBackendInterface.getInstance().getPlayerRef();
            HttpUriRequest request= HttpUriRequestFactory
                    .getRequest("http://onebeat.pythonanywhere.com/getUser?id=" + id);


            BackendDownloader<List<String>> ddl =
                    new BackendDownloader<>(request, new SubscribedPlaylistParser());

            ddl.execute(pendingData);

        } catch ( IOException  e) {
            e.printStackTrace();
            throw new BadInterfaceRequestException();
        }


        return pendingData;


    }





}



