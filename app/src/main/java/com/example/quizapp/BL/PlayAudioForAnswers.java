package com.example.quizapp.BL;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.quizapp.R;

public class PlayAudioForAnswers {


    private Context mContext;
    private MediaPlayer mediaPlayer;


    public PlayAudioForAnswers(Context mContext) {
        this.mContext = mContext;
    }


    public void setAudioForAnswers(final int flag){

        switch (flag){

            case 1:
                int correctAudio = R.raw.correct;
                playMusic(correctAudio);
                break;
            case 2:

                int wrongAudio = R.raw.wrong;
                playMusic(wrongAudio);
                break;

        }
    }

    private void playMusic(int audioFile) {
        //Register a callback to be invoked when the media source is ready for playback.
        mediaPlayer = MediaPlayer.create(mContext,audioFile);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        //Register a callback to be invoked when a seek operation has been completed.
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });

    }
}
