package ch.epfl.sweng.onebeat.frontEnd.network;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hugo on 01.12.15.
 */
public class HttpUriRequestFactory {





    public static HttpUriRequest getRequest(String strUrl) throws IOException {

        return new HttpGet(strUrl);

    }




    public static HttpUriRequest postRequest(String strUrl, Map<String,String> namesValues)
            throws IOException {

        HttpPost post = new HttpPost(strUrl);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for(Map.Entry<String,String> entry : namesValues.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));


        return post;
    }






}

