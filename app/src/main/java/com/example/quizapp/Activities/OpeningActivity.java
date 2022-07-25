package com.example.quizapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.quizapp.BL.QuestionViewModel;
import com.example.quizapp.R;

public class OpeningActivity extends AppCompatActivity {
    private Button getStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        getStartedButton = findViewById(R.id.startButton);
        QuestionViewModel view = new ViewModelProvider(this).get(QuestionViewModel.class);
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this is how we go to login
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(OpeningActivity.this).toBundle();
                Intent i = new Intent(OpeningActivity.this, LoginActivity.class);
                startActivity(i, b);

            }
        });

    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
        finish();
    }
}


