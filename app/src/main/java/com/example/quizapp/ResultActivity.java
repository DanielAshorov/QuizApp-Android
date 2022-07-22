package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView txtHighScore;
    TextView txtTotalQuizQuestion, txtCorrectQuestion, txtWrongQuestion;
    Button btMainMenu;
    int highScore =0;
    private String category;
    private static final String SHARED_PREFERENCE = "shared_preference";
    private static final String SHARED_PREFERENCE_HIGH_SCORE = "shared_preference_high_score_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtHighScore = findViewById(R.id.result_tv_HighScore);
        txtTotalQuizQuestion = findViewById(R.id.result_tv_Num_of_Ques);
        txtCorrectQuestion = findViewById(R.id.result_tv_correct_Ques);
        txtWrongQuestion = findViewById(R.id.result_tv_wrong_Ques);
        btMainMenu = findViewById(R.id.bt_result_main_menu);
        btMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, CategoryActivity.class);
                startActivity(intent);

            }
        });

        loadHighScore();

        Intent intent = getIntent();
        category  = intent.getStringExtra("Category");
        int score = intent.getIntExtra("UserScore",0);
        int totalQuestion = intent.getIntExtra("TotalQuizQuestions",0);
        int correctQuestions = intent.getIntExtra("CorrectQuestions",0);
        int wrongQuestion = intent.getIntExtra("WrongQuestions",0);

        txtTotalQuizQuestion.setText(txtTotalQuizQuestion.getText().toString() + String.valueOf(totalQuestion));
        txtCorrectQuestion.setText(txtCorrectQuestion.getText().toString()  + String.valueOf(correctQuestions));
        txtWrongQuestion.setText(txtWrongQuestion.getText().toString() + String.valueOf(wrongQuestion));


        if (score > highScore){
            updateScore(score);
        }
        else
        {
            txtHighScore.setText(txtHighScore.getText().toString() + String.valueOf(highScore));
        }
    }

    private void updateScore(int score) {

        highScore = score;

        txtHighScore.setText(txtHighScore.getText().toString() + String.valueOf(highScore));

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE + category, highScore);
        editor.apply();
    }

    private void loadHighScore() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERENCE_HIGH_SCORE + category,0);
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(ResultActivity.this, CategoryActivity.class));
    }

}