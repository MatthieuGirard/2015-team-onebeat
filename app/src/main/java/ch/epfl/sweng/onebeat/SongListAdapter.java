package ch.epfl.sweng.onebeat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Karim on 06-Nov-15.
 *
 * This custom class will specify to ListView objects how to properly display a Song class object
 */
public class SongListAdapter extends ArrayAdapter<Song> {
    public SongListAdapter(Context context, ArrayList<Song> songs) {
        super(context, R.layout.song_item_list_view, songs);
        Log.d("KEINFO", "was able to super");
    }

    /**
     * ViewHolders are used to cache fields of the song_list_item_view. Since it is expensive to
     * search for views by id, we just store the separate view objects in the tag field of each
     * view. Thus saving time == better performance
     */
    private static class ViewHolder {
        TextView title;
        TextView artist;
        TextView duration;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the song for this position
        Song song = getItem(position);
        ViewHolder viewHolder; // View lookup cache stored in tag

        // Check if an existing view is being reused
        if (convertView == null) {
            Log.d("KEINFO", "No previous view found");
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.song_item_list_view, parent, false);

            viewHolder.title = (TextView) convertView.findViewById(R.id.song_title);
            viewHolder.artist = (TextView) convertView.findViewById(R.id.artist);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.duration);

            convertView.setTag(viewHolder);
        } else {
            Log.d("KEINFO", "Reusing a view holder");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(song.title);
        viewHolder.artist.setText(song.artist);
        viewHolder.duration.setText(song.duration);

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}