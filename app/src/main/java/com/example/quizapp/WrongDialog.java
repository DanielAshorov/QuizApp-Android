package com.example.quizapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class WrongDialog {

    private Context mContext;
    private Dialog wrongAnswerDialog;
    private QuizActivity mQuizActivity;
    private static String answerText;

    WrongDialog(Context mContext, String answerText) {
        this.mContext = mContext;
        this.answerText = answerText;
    }

    void showWrongDialog(String correctAnswer,QuizActivity quizActivity){

        mQuizActivity = quizActivity;
        wrongAnswerDialog = new Dialog(mContext);
        wrongAnswerDialog.setContentView(R.layout.wrong_dialog);
        final Button btWrongAnswerDialog = (Button) wrongAnswerDialog.findViewById(R.id.bt_wrongDialog);
        TextView textView = (TextView) wrongAnswerDialog.findViewById(R.id.textView_Correct_Answer);

        textView.setText(answerText + " " + correctAnswer);
        btWrongAnswerDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                wrongAnswerDialog.dismiss();
                mQuizActivity.setQuestionView();
            }
        });

        wrongAnswerDialog.show();
        wrongAnswerDialog.setCancelable(false);
        wrongAnswerDialog.setCanceledOnTouchOutside(false);
        wrongAnswerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }
}
