package com.hesham.musicplayer;

import android.media.MediaPlayer;
import android.widget.ImageButton;

public class OnCompleteObj implements MediaPlayer.OnCompletionListener {

    private static OnCompleteObj obj;
    private ImageButton btn_play;
    private int imagesource;
    private MediaPlayer mediaPlayer;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private OnCompleteObj() {}

    public static OnCompleteObj getInstance()
    {
        if (obj == null)
            obj = new OnCompleteObj();
        return obj;
    }

    public void setBtn_play(ImageButton btn_play) {
        this.btn_play = btn_play;
    }

    public void setImagesource(int imagesource) {
        this.imagesource = imagesource;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.btn_play.setImageResource(imagesource);
    }



}


