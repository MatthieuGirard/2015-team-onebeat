package ch.epfl.sweng.onebeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseActivity extends AppCompatActivity {

    public String s = "{\"info\": {\"num_results\": 1, \"limit\": 100, \"offset\": 0, \"query\": \"foooo\", \"type\": \"track\", \"page\": 1}, \"tracks\": [{\"album\": {\"released\": \"2006\", \"href\": \"spotify:album:4VCxFGCoJG1w7RmxtaKsjX\", \"name\": \"Suffering Man\", \"availability\": {\"territories\": \"AD AR AT AU BE BG BO BR CA CH CL CO CR CY CZ DE DK DO EC EE ES FI FR GB GR GT HK HN HR HU IE IS IT LI LT LU LV MC MT MX MY NI NL NO NZ PA PE PH PL PT PY RO SE SG SI SK SV TR TW US UY\"}}, \"name\": \"Black Mother Foooo\", \"external-ids\": [{\"type\": \"isrc\", \"id\": \"JMB110900182\"}], \"popularity\": \"0.02\", \"explicit\": true, \"length\": 347.693, \"href\": \"spotify:track:0uIpyzeR7wSmDCErymJHmc\", \"artists\": [{\"href\": \"spotify:artist:7Lij2ZLJJQOfGojVR3Wmqa\", \"name\": \"Anthony B\"}], \"track-number\": \"11\"}]}";

    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        parseJSON(s);

    }

    public void parseJSON(String s) {
        try {
            JSONObject json = new JSONObject(s);

            JSONObject info = json.getJSONObject("info");

            //textView1.setText(json.toString());

            //textView2.setText(info.toString());

            int numResults = info.getInt("num_results");
            int numPages = info.getInt("page");

            JSONArray jsonTracks = json.optJSONArray("tracks");

            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.resultsLayout);

            for (int i = 0; i < jsonTracks.length(); i++) {
                TextView textView = new TextView(this);
                textView.setText(jsonTracks.getJSONObject(i).getJSONArray("artists").getJSONObject(0).getString("name")
                        +" - "+jsonTracks.getJSONObject(i).getString("name"));

                relativeLayout.addView(textView);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
