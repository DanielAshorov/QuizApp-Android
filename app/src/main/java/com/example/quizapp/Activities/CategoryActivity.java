package com.example.quizapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizapp.R;

public class CategoryActivity extends AppCompatActivity  implements View.OnClickListener {
    Button catSport, catMusic, catFood, catMovies, catAnimals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        catSport = findViewById(R.id.cat_Sport);
        catMusic = findViewById(R.id.cat_Music);
        catFood = findViewById(R.id.cat_Food);
        catMovies = findViewById(R.id.cat_Movies);
        catAnimals = findViewById(R.id.cat_Animals);

        catSport.setOnClickListener(this);
        catMusic.setOnClickListener(this);
        catFood.setOnClickListener(this);
        catMovies.setOnClickListener(this);
        catAnimals.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.cat_Sport:
                Intent intentSport = new Intent(CategoryActivity.this, QuizActivity.class);
                intentSport.putExtra("Category", catSport.getText().toString());
                intentSport.putExtra("GlobalCategory", getString(R.string.global_sport));
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(CategoryActivity.this).toBundle();
                startActivity(intentSport, b);
                break;

            case R.id.cat_Music:
                Intent intentMusic = new Intent(CategoryActivity.this,QuizActivity.class);
                intentMusic.putExtra("Category", catMusic.getText().toString());
                intentMusic.putExtra("GlobalCategory", getString(R.string.global_music));
                Bundle c = ActivityOptions.makeSceneTransitionAnimation(CategoryActivity.this).toBundle();
                startActivity(intentMusic, c);
                break;

            case R.id.cat_Food:
                catFood.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentFood = new Intent(CategoryActivity.this,QuizActivity.class);
                intentFood.putExtra("Category", catFood.getText().toString());
                intentFood.putExtra("GlobalCategory", getString(R.string.global_food));
                Bundle d = ActivityOptions.makeSceneTransitionAnimation(CategoryActivity.this).toBundle();
                startActivity(intentFood, d);
                break;

            case R.id.cat_Movies:
                catMovies.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentMovies = new Intent(CategoryActivity.this,QuizActivity.class);
                intentMovies.putExtra("Category", catMovies.getText().toString());
                intentMovies.putExtra("GlobalCategory", getString(R.string.global_movies));
                Bundle e = ActivityOptions.makeSceneTransitionAnimation(CategoryActivity.this).toBundle();
                startActivity(intentMovies, e);
                break;

            case R.id.cat_Animals:
                catAnimals.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentAnimals = new Intent(CategoryActivity.this,QuizActivity.class);
                intentAnimals.putExtra("Category", catAnimals.getText().toString());
                intentAnimals.putExtra("GlobalCategory", getString(R.string.global_animals));
                Bundle f = ActivityOptions.makeSceneTransitionAnimation(CategoryActivity.this).toBundle();
                startActivity(intentAnimals, f);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(CategoryActivity.this, OpeningActivity.class));
        Bundle g = ActivityOptions.makeSceneTransitionAnimation(CategoryActivity.this).toBundle();
        Intent i = new Intent(CategoryActivity.this, OpeningActivity.class);
        startActivity(i, g);

    }
}