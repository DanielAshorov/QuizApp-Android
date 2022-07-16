package com.example.quizapp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    TextView txtQuestion;
    TextView textViewScore,textViewQuestionCount,textViewCountDownTimer;
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rbGroup;
    Button buttonNext;
    boolean answerd = false;
    List<Questions> quesList;
    Questions currentQ;
    private int questionCounter=0,questionTotalCount;
    private QuestionViewModel questionViewModel;
    private ColorStateList textColorofButtons;
    private Handler handler = new Handler();
    private int correctAns = 0,wrongAns =0;
    private int score=0;
    private TimerDialog timerDialog;
    private WrongDialog wrongDialog;
    private CorrectDialog correctDialog;
    private int totalSizeofQuiz;
    private int FLAG = 0;
    private PlayAudioForAnswers playAudioForAnswers;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftinMillis;
    private long backPressedTime;
    private String CategoryValue ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        setupUI();
        // this is used to change the text colors of the buttons
        textColorofButtons = rb1.getTextColors();



        timerDialog =  new TimerDialog(this);
        wrongDialog =  new WrongDialog(this);
        correctDialog = new CorrectDialog(this);
        playAudioForAnswers = new PlayAudioForAnswers(this);

        Intent intent = getIntent();
        CategoryValue = intent.getStringExtra("Category");

        String language = getString(R.string.language);
        Log.d("TRIVIA", "onCreate: before viewmodel");
        questionViewModel = ViewModelProviders.of(this).get(QuestionViewModel.class);
        Log.d("TRIVIA", "onCreate: calling getAllQuestionByCategory with category=" + CategoryValue);

        questionViewModel.getAllQuestionByCategory(CategoryValue,language).observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable List<Questions> questions) {
                //first time check the questions
                if (questions == null || questions.size() == 0) {
                    return;
                }
                Log.d("TRIVIA", "questions: " + questions.size());

                fetchContent(questions);

            }
        });
        Log.i("DATATA","onCreate() in QuizActivity");
    }


    void setupUI(){

        textViewCountDownTimer = findViewById(R.id.txtTimer);
        textViewScore = findViewById(R.id.txtScore);
        textViewQuestionCount = findViewById(R.id.txtTotalQuestion);
        txtQuestion = findViewById(R.id.txtQuetsionContainer);
        rbGroup = findViewById(R.id.raido_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        rb4 = findViewById(R.id.radio_button4);
        buttonNext = findViewById(R.id.button_Next);

    }

        //get all questions from DB and make shuffle between question according category
    private void fetchContent(List<Questions> questions) {
        quesList = questions;
        Collections.shuffle(quesList);
        startQuiz();
    }

    // SetQuestionsView() Method  move all questions according to category
    public void setQuestionView(){

        rbGroup.clearCheck();
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
        rb1.setTextColor(Color.WHITE);
        rb2.setTextColor(Color.WHITE);
        rb3.setTextColor(Color.WHITE);
        rb4.setTextColor(Color.WHITE);
        questionTotalCount = quesList.size() + 1;

        if (questionCounter < questionTotalCount -1){

            currentQ = quesList.get(questionCounter);
            txtQuestion.setText(currentQ.getQuestion());
            rb1.setText(currentQ.getOptA());
            rb2.setText(currentQ.getOptB());
            rb3.setText(currentQ.getOptC());
            rb4.setText(currentQ.getOptD());
            questionCounter++;
            answerd = false;

            textViewQuestionCount.setText( "Questions" + questionCounter +"/" +(questionTotalCount-1));
            Log.d("TRIVIA", "current question: " + currentQ.getQuestion());
            timeLeftinMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        }else {

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonNext.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resultData();
                }
            },2000);
        }
    }


    private void startQuiz() {

        setQuestionView();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId){

                    case R.id.radio_button1:

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_a));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
                        break;

                    case R.id.radio_button2:

                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_b));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
                        break;

                    case R.id.radio_button3:

                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_c));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_d));
                        break;

                    case R.id.radio_button4:

                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.selected_option_d));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_b));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_c));
                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.default_option_a));
                        break;

                }
            }
        });



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answerd){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){

                        quizOpeartion();

                    }else {
                        Toast.makeText(QuizActivity.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean paused = true;
    //call moment before it's appear on screen
    @Override
    protected void onResume() {
        super.onResume();
        paused = false;
    }
    //call moment after no see activity
    @Override
    protected void onPause() {
        super.onPause();
        paused = true;
    }

    private void quizOpeartion() {

        answerd = true;
        countDownTimer.cancel();
        RadioButton rbselected =  findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbselected) +1;
        checkSolution(answerNr,rbselected);

    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQ.getAnswer()) {

            case 1:

                if (currentQ.getAnswer() == answerNr) {
              rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb1.setTextColor(Color.WHITE);
                    correctAns++;
                    score +=10;  // score = score + 10
                    textViewScore.setText(textViewScore.getText().toString() + String.valueOf(score));
                    correctDialog.correctDialog(score,this);
                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                } else {

                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb1.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);
                }
                break;


            case 2:

                if (currentQ.getAnswer() == answerNr) {
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb2.setTextColor(Color.WHITE);
                    correctAns++;
                    score +=10;  // score = score + 10
                    textViewScore.setText(textViewScore.getText().toString()+ String.valueOf(score));
                    correctDialog.correctDialog(score,this);
                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                } else
                    {
                        changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb2.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);
                }
                break;

            case 3:

                if (currentQ.getAnswer() == answerNr) {

                     rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                     rb3.setTextColor(Color.WHITE);
                    correctAns++;
                    score +=10;  // score = score + 10
                    textViewScore.setText(textViewScore.getText().toString() + String.valueOf(score));
                    correctDialog.correctDialog(score,this);
                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                } else {

                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb3.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);
                }
                break;


            case 4:

                if (currentQ.getAnswer() == answerNr) {

                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.when_answer_correct));
                    rb4.setTextColor(Color.WHITE);
                    correctAns++;
                    score +=10;  // score = score + 10
                    textViewScore.setText(textViewScore.getText().toString() + String.valueOf(score));
                    correctDialog.correctDialog(score,this);
                    FLAG = 1;
                    playAudioForAnswers.setAudioforAnswers(FLAG);

                } else {

                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    final String correctAnswer = (String) rb4.getText();
                    wrongDialog.WrongDialog(correctAnswer,this);
                    FLAG = 2;
                    playAudioForAnswers.setAudioforAnswers(FLAG);
                }
                break;
        }

        if (questionCounter == questionTotalCount){
            buttonNext.setText("Confirm and Finish");
        }

    }

    private void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_answer_wrong));
        rbselected.setTextColor(Color.WHITE);
    }


    // The timer code
    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftinMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftinMillis = 0;
                updateCountDownText();

            }
        }.start();

    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftinMillis/1000) /60;
        int seconds = (int) (timeLeftinMillis/1000) %60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        textViewCountDownTimer.setText(timeFormatted);

        if (timeLeftinMillis <10000){

            textViewCountDownTimer.setTextColor(Color.RED);
            FLAG = 3;
            playAudioForAnswers.setAudioforAnswers(FLAG);

        }else {
            textViewCountDownTimer.setTextColor(ContextCompat.getColor(this,R.color.timerFontColor));
        }

        if (timeLeftinMillis == 0){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (!paused) {
                        timerDialog.timerDialog();
                    }
                }
            },2000);

        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null){
            countDownTimer.cancel();
        }

        Log.i("DATATA","onDestroy in QuizActivity");

    }


    private void resultData(){
        finish(); // close activity
        Intent resultofQuiz = new Intent(QuizActivity.this,ResultActivity.class);
        resultofQuiz.putExtra("UserScore", score);
        resultofQuiz.putExtra("TotalQuizQuestions",(questionTotalCount -1));
        resultofQuiz.putExtra("CorrectQuestions",correctAns);
        resultofQuiz.putExtra("WrongQuestions",wrongAns);
        startActivity(resultofQuiz);

    }


    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(QuizActivity.this, CategoryActivity.class);
            startActivity(intent);

        }else {

        }

        backPressedTime = System.currentTimeMillis();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DATATA","onStop() in QuizActivity");
        finish();
    }
}