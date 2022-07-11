package com.example.quizapp;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {


    private final static int EXIT_CODE = 100;

    ImageView imageViewSplashLogo;
    //TextView textViewGoQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageViewSplashLogo = findViewById(R.id.splash_imgView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transition);
        imageViewSplashLogo.setAnimation(animation);

        QuestionRoomDatabase.getInstance(this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {


                try {
                    sleep(4000);

                } catch (Exception e) {
                    e.printStackTrace();

                } finally {

                    GoPlayActivity();
                }


            }
        });
        thread.start();
    }

    private void GoPlayActivity() {

        startActivity(new Intent(SplashActivity.this, PlayActivity.class));
        /**
         * Call this when your activity is done and should be closed.  The
         * ActivityResult is propagated back to whoever launched you via
         * onActivityResult().
         */
        finish();
    }
}

