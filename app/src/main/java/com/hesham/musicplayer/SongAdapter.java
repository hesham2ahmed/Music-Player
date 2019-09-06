package com.hesham.musicplayer;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {



    public SongAdapter(Activity context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }

        Song currentSong = getItem(position);

        TextView song_text = listItemView.findViewById(R.id.song_title);
        song_text.setText(currentSong.getSong_title());

        TextView song_artist = listItemView.findViewById(R.id.song_artist);
        song_artist.setText(currentSong.getSong_artist().toLowerCase()+" , "+currentSong.getSong_alboum().toLowerCase());

        TextView song_duration = listItemView.findViewById(R.id.song_duration);
        song_duration.setText(currentSong.getSong_duration());

        return listItemView;
    }
}
