package com.example.quizapp.BL;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.Activities.QuizActivity;
import com.example.quizapp.R;

public class CorrectDialog {
    private Context mContext;
    private Dialog correctDialog;

    private QuizActivity mQuizActivity;

    public CorrectDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void correctDialog(int score,QuizActivity quizActivity){

        mQuizActivity = quizActivity;


        correctDialog = new Dialog(mContext);

        correctDialog.setContentView(R.layout.correct_dialog);
        final Button buttonCorrectDialog = (Button) correctDialog.findViewById(R.id.button_Score_Dialog);

        Score(score);

        buttonCorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                correctDialog.dismiss();
                mQuizActivity.setQuestionView();

            }
        });

        correctDialog.show();
        correctDialog.setCancelable(false);
        correctDialog.setCanceledOnTouchOutside(false);
        correctDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void Score(int score) {

        TextView textScore = correctDialog.findViewById(R.id.textView_Score);
        textScore.setText("Score: " + String.valueOf(score));
    }

}
