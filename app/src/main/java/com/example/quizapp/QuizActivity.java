package com.example.quizapp;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
    private Toast toast;
    TextView textViewScore,textViewQuestionCount,textViewCountDownTimer;
    private static String scoreText;
    private static String questionText;
    RadioButton rb1,rb2,rb3,rb4;
    RadioGroup rbGroup;
    Button buttonNext;
    boolean answered = false;
    List<Questions> quesList;
    Questions currentQ;
    private int questionCounter=0,questionTotalCount;
    private QuestionViewModel questionViewModel;
    private ColorStateList textColorButtons;
    private Handler handler = new Handler();
    private int correctAns = 0, wrongAns =0;
    private int score=0;
    private TimerDialog timerDialog;
    private WrongDialog wrongDialog;
    private CorrectDialog correctDialog;
    private int totalSizeofQuiz;
    private int FLAG = 0;
    private PlayAudioForAnswers playAudioForAnswers;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private long backPressedTime;
    private String CategoryValue ="";

    public QuizActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        setupUI();
        // this is used to change the text colors of the buttons
        textColorButtons = rb1.getTextColors();
        String answerText = getString(R.string.the_correct_answer_is);
        timerDialog =  new TimerDialog(this);
        wrongDialog =  new WrongDialog(this, answerText);
        correctDialog = new CorrectDialog(this);
        playAudioForAnswers = new PlayAudioForAnswers(this);

        Intent intent = getIntent();
        CategoryValue = intent.getStringExtra("Category");

        String language = getString(R.string.language);
        Log.d("TRIVIA", "onCreate: before viewModel");
        questionViewModel = new ViewModelProvider(this).get(QuestionViewModel.class);
        Log.d("TRIVIA", "onCreate: calling getAllQuestionByCategory with category=" + CategoryValue);

        questionViewModel.getAllQuestionByCategory(CategoryValue, language).observe(this, new Observer<List<Questions>>() {
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
        Log.i("DATA","onCreate() in QuizActivity");
    }


    void setupUI(){

        questionText = getString(R.string.question_txt);
        scoreText = getString(R.string.txtScore);
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
            answered = false;


            textViewQuestionCount.setText( questionText + " " + questionCounter +"/" +(questionTotalCount-1));
            Log.d("TRIVIA", "current question: " + currentQ.getQuestion());
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
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
                        //button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                        rb1.setTextColor(Color.RED);
                        rb2.setTextColor(Color.WHITE);
                        rb3.setTextColor(Color.WHITE);
                        rb4.setTextColor(Color.WHITE);
                        break;

                    case R.id.radio_button2:

                        rb2.setTextColor(Color.RED);
                        rb1.setTextColor(Color.WHITE);
                        rb3.setTextColor(Color.WHITE);
                        rb4.setTextColor(Color.WHITE);
                        break;

                    case R.id.radio_button3:

                        rb3.setTextColor(Color.RED);
                        rb2.setTextColor(Color.WHITE);
                        rb1.setTextColor(Color.WHITE);
                        rb4.setTextColor(Color.WHITE);
                        break;

                    case R.id.radio_button4:
                        rb4.setTextColor(Color.RED);
                        rb2.setTextColor(Color.WHITE);
                        rb3.setTextColor(Color.WHITE);
                        rb1.setTextColor(Color.WHITE);
                        break;

                }
            }
        });



        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){

                        quizOperation();

                    }else {
                        String selectAnswer = getString(R.string.select_answer);
                        Toast.makeText(QuizActivity.this, selectAnswer, Toast.LENGTH_SHORT).show();
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

    private void quizOperation() {

        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected =  findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) +1;
        checkSolution(answerNr,rbSelected);

    }

    private void checkSolution(int answerNr, RadioButton rbSelected) {

        if(currentQ.getAnswer() == answerNr)
        {
            correctAns++;
            score +=10;
            textViewScore.setText(scoreText + String.valueOf(score));
            rbSelected.setTextColor(Color.GREEN);
            correctDialog.correctDialog(score,this);
            FLAG = 1;
        }
        else {
            RadioButton answer;

            switch(currentQ.getAnswer())
            {
                case 1:
                    answer = rb1;
                    break;
                case 2:
                    answer = rb2;
                    break;
                case 3:
                    answer = rb3;
                    break;
                case 4:
                    answer = rb4;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + currentQ.getAnswer());
            }

            wrongAns++;
            final String correctAnswer = (String) answer.getText();
            wrongDialog.showWrongDialog(correctAnswer,this);
            FLAG = 2;
        }

        playAudioForAnswers.setAudioforAnswers(FLAG);

        if (questionCounter == questionTotalCount)
            buttonNext.setText("Confirm and Finish");
    }


    // The timer code
    private void startCountDown() {

        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                timeLeftInMillis = 0;
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText() {

        int minutes = (int) (timeLeftInMillis /1000) /60;
        int seconds = (int) (timeLeftInMillis /1000) %60;

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        textViewCountDownTimer.setText(timeFormatted);

        if (timeLeftInMillis <10000){

            textViewCountDownTimer.setTextColor(Color.RED);
            FLAG = 3;
            playAudioForAnswers.setAudioforAnswers(FLAG);

        }else {
            textViewCountDownTimer.setTextColor(ContextCompat.getColor(this,R.color.timerFontColor));
        }

        if (timeLeftInMillis == 0){

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
        Intent QuizResult = new Intent(QuizActivity.this,ResultActivity.class);
        QuizResult.putExtra("UserScore", score);
        QuizResult.putExtra("TotalQuizQuestions",(questionTotalCount -1));
        QuizResult.putExtra("CorrectQuestions",correctAns);
        QuizResult.putExtra("WrongQuestions",wrongAns);
        startActivity(QuizResult);

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DATA","onStop() in QuizActivity");
        finish();
    }

    @Override
    public void onBackPressed()
    {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast.cancel();
            startActivity(new Intent(QuizActivity.this, CategoryActivity.class));
            return;
        }
        else {
            String clickTwice = getString(R.string.double_back_bt_exit);
            toast = Toast.makeText(getBaseContext(), clickTwice, Toast.LENGTH_SHORT);
            toast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}