package com.example.quizapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.R;

public class ResultActivity extends AppCompatActivity {

    TextView txtHighScore;
    TextView txtTotalQuizQuestion, txtCorrectQuestion, txtWrongQuestion, txtYourScore;
    Button playAgain;
    int highScore = 0;
    private String category;
    private static final String SHARED_PREFERENCE = "shared_preference";
    private static final String SHARED_PREFERENCE_HIGH_SCORE = "shared_preference_high_score_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        txtYourScore = findViewById(R.id.result_tv_your_score);
        txtHighScore = findViewById(R.id.result_tv_HighScore);
        txtTotalQuizQuestion = findViewById(R.id.result_tv_Num_of_Ques);
        txtCorrectQuestion = findViewById(R.id.result_tv_correct_Ques);
        txtWrongQuestion = findViewById(R.id.result_tv_wrong_Ques);
        playAgain = findViewById(R.id.bt_result_main_menu);

        Intent intent = getIntent();
        int score = intent.getIntExtra("UserScore",0);
        int totalQuestion = intent.getIntExtra("TotalQuizQuestions",0);
        int correctQuestions = intent.getIntExtra("CorrectQuestions",0);
        int wrongQuestion = intent.getIntExtra("WrongQuestions",0);
        category  = intent.getStringExtra("Category");

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = ActivityOptions.makeSceneTransitionAnimation(ResultActivity.this).toBundle();
                Intent intent = new Intent(ResultActivity.this, CategoryActivity.class);
                startActivity(intent, b);

            }
        });

        loadHighScore();

        txtYourScore.setText(txtYourScore.getText().toString() +  " " + String.valueOf(score));
        txtTotalQuizQuestion.setText(txtTotalQuizQuestion.getText().toString() + " " +String.valueOf(totalQuestion));
        txtCorrectQuestion.setText(txtCorrectQuestion.getText().toString()  +  " " +String.valueOf(correctQuestions));
        txtWrongQuestion.setText(txtWrongQuestion.getText().toString() + " " + String.valueOf(wrongQuestion));

        if (score > highScore){
            updateScore(score);
            Intent newHighScoreIntent = new Intent(ResultActivity.this, NewHighScore.class);
            newHighScoreIntent.putExtra("newHighScore", score);
            Bundle b = ActivityOptions.makeSceneTransitionAnimation(ResultActivity.this).toBundle();
            startActivity(newHighScoreIntent, b);

            startActivity(newHighScoreIntent);
        }
        else
        {
            txtHighScore.setText(txtHighScore.getText().toString() +  " " + String.valueOf(highScore));
        }
    }

    private void updateScore(int score) {

        highScore = score;

        txtHighScore.setText(txtHighScore.getText().toString() + " " + String.valueOf(highScore));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String key = SHARED_PREFERENCE_HIGH_SCORE + category;
        editor.putInt( key, highScore);
        editor.apply();
    }

    private void loadHighScore() {

        String key =SHARED_PREFERENCE_HIGH_SCORE + category;
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(key,0);
    }

    @Override
    public void onBackPressed()
    {
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(ResultActivity.this).toBundle();
        Intent i = new Intent(ResultActivity.this, CategoryActivity.class);
        startActivity(i, b);
    }

}