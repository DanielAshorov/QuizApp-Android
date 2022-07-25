package com.example.quizapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.quizapp.R;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class NewHighScore extends AppCompatActivity {

    private KonfettiView konfettiView = null;
    private Shape.DrawableShape drawableShape = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_high_score);
        Intent intent = getIntent();
        int newHighScore = intent.getIntExtra("newHighScore", 0);
        TextView highScoreText = findViewById(R.id.newHighScoreTextV);
        highScoreText.setText(highScoreText.getText() + " " + String.valueOf(newHighScore));
        konfettiView = findViewById(R.id.konfettiView);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.heart);
        drawableShape = new Shape.DrawableShape(drawable, true);
        EmitterConfig emitterConfig = new Emitter(4000, TimeUnit.MILLISECONDS).max(100);
        konfettiView.start(
                new PartyFactory(emitterConfig)
                        .spread(360)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, drawableShape))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 30f)
                        .position(new Position.Relative(0.5, 0.3))
                        .build()
        );
    }

    @Override
    public void onBackPressed()
    {
        finishTask();
    }

    private void finishTask()
    {
        finishAffinity();
        startActivity(new Intent(NewHighScore.this, CategoryActivity.class));
    }
}