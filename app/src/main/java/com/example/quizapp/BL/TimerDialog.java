package com.example.quizapp.BL;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

import com.example.quizapp.Activities.CategoryActivity;
import com.example.quizapp.R;

public class TimerDialog {

    private Context mContext;
    private Dialog timerDialog;

    public TimerDialog(Context mContext) {
        this.mContext = mContext;
    }

    public void timerDialog(){

        timerDialog = new Dialog(mContext);

        timerDialog.setContentView(R.layout.timer_dialog);
        final Button btTimerDialog = (Button) timerDialog.findViewById(R.id.bt_timerDialog);


        btTimerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timerDialog.dismiss();
                Intent intent = new Intent(mContext, CategoryActivity.class);
                mContext.startActivity(intent);

            }
        });

        timerDialog.show();
        timerDialog.setCancelable(false);
        timerDialog.setCanceledOnTouchOutside(false);
        timerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}