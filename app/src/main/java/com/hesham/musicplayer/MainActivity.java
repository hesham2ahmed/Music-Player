package com.hesham.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.lifecycle.LifecycleObserver;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LifecycleObserver {


    private MediaPlayer mediaPlayer = null;
    private int song_position;
    private OnCompleteObj onCompleteObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // set a custom bar
            Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(toolbar);

            // create listview
            ListView listView = findViewById(R.id.list_view);

            // creat objects of song.class and store it in arraylist
            final ArrayList<Song> songs = ListRaw();


            //set play Button
            final ImageButton btn_play = findViewById(R.id.btn_play);

            // creat an object of custom arrayadapter
            final SongAdapter songAdapter = new SongAdapter(this,R.layout.activity_main,songs);

            // set adapter
            listView.setAdapter(songAdapter);



            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long ID) {

                    song_position = position;

                    PlaySong(position,songs,btn_play);


                }
            });


            btn_play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mediaPlayer != null && mediaPlayer.isPlaying() ){
                     mediaPlayer.pause();
                     btn_play.setImageResource(R.drawable.ic_paly_arrow);
                    }

                    else if(mediaPlayer == null )
                        PlaySong(0,songs,btn_play);

                    else if(!mediaPlayer.isPlaying() && mediaPlayer !=null) {

                        mediaPlayer.start();
                        btn_play.setImageResource(R.drawable.ic_pause_circle);
                    }
                }
            });

    }


    /**
     * To free memory from MediaPlayer object and assign it to NULL
     */
    public void ReleaseMedia(){
        if(mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReleaseMedia();
    }

    /**
     * to list all songs in raw dic
     * @return ArrayList<song></>
     */
    public ArrayList<Song> ListRaw(){
        Field[] listraw = R.raw.class.getFields();
        int lenght = listraw.length;
        // Prepare arraylist to have objects
        final ArrayList<Song> songs = new ArrayList<>();

        for(int i = 0 ; i< lenght ; i++){
            try {
                songs.add(new Song(listraw[i].getInt(listraw[i]),MainActivity.this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return songs;
    }

    /**
     * to play a song with particular position
     * @param position
     * @param songs
     */
    public void PlaySong(int position ,ArrayList<Song> songs ,ImageButton btn_play ){
        Song CurrentSong  = songs.get(position);

        TextView song_title = findViewById(R.id.song_title_bar);
        song_title.setText(CurrentSong.getSong_title());

        TextView song_artist = findViewById(R.id.song_artist_bar);
        song_artist.setText(CurrentSong.getSong_artist());

        ReleaseMedia();

        mediaPlayer = MediaPlayer.create(MainActivity.this,CurrentSong.getSong_id_resourse());
        mediaPlayer.start();

        // Create an OnCompleteListner Object
        onCompleteObj = onCompleteObj.getInstance();
        onCompleteObj.setBtn_play(btn_play);
        onCompleteObj.setImagesource(R.drawable.ic_paly_arrow);

        //Start mediaPlayer (Song)
        mediaPlayer.start();

        btn_play.setImageResource(R.drawable.ic_pause_circle);


        mediaPlayer.setOnCompletionListener(onCompleteObj);

    }
}

