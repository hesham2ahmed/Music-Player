package com.hesham.musicplayer;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.concurrent.TimeUnit;

public class Song {


    private String song_title;
    private String song_duration;
    private String song_artist;
    private String song_alboum;
    private int song_id_resourse;
    private String alboum_image_resourse;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private Context context;



    // Constructor //
    public Song(int song_id_resourse, Context context){

        this.song_id_resourse = song_id_resourse;
        this.context = context;
        SetInformatiob();
    }



    public void SetInformatiob(){
       Uri myUri = Uri.parse("android.resource://com.hesham.musicplayer/" + song_id_resourse);
       mediaMetadataRetriever = new MediaMetadataRetriever();
       mediaMetadataRetriever.setDataSource(context, myUri);
       setSong_duration();
       setSong_title();
       setSong_artist();
       setSong_alboum();
    }

    // Set Song title
    public void setSong_title() {
        this.song_title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);}

    // Set Song duration
    public void setSong_duration(){
        this.song_duration = ConvertDurationInMinutes(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
    }

    // Set Song artist
    public void setSong_artist(){
        this.song_artist = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
    }

    // Set Song alboum
    public void setSong_alboum(){
        this.song_alboum = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
    }

    // Getter functions
    public String getSong_duration() {
        return song_duration;}

    public int getSong_id_resourse() {
        return song_id_resourse;}

    public String getSong_title() {
        return song_title;}

    public String getSong_artist() {
        return song_artist;}

    public String getSong_alboum() {
        return song_alboum;}

    /**
     * convert and edit duration to minutes and seconds
      * @param Duration
     * @return
     */
    public String ConvertDurationInMinutes(String Duration){
        int duration = Integer.parseInt(Duration);
        return  String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}