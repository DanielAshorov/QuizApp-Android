package com.example.quizapp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                catSport.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentSport = new Intent(CategoryActivity.this,QuizActivity.class);
                intentSport.putExtra("Category", catSport.getText().toString());
                startActivity(intentSport);
                break;

            case R.id.cat_Music:
                catMusic.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentMusic = new Intent(CategoryActivity.this,QuizActivity.class);
                intentMusic.putExtra("Category", catMusic.getText().toString());
                startActivity(intentMusic);
                break;

            case R.id.cat_Food:
                catFood.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentScience = new Intent(CategoryActivity.this,QuizActivity.class);
                intentScience.putExtra("Category", catFood.getText().toString());
                startActivity(intentScience);
                break;

            case R.id.cat_Movies:
                catMovies.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentMovies = new Intent(CategoryActivity.this,QuizActivity.class);
                intentMovies.putExtra("Category", catMovies.getText().toString());
                startActivity(intentMovies);
                break;

            case R.id.cat_Animals:
                catAnimals.setBackgroundColor(Color.rgb(45, 159, 148));
                Intent intentTechnology = new Intent(CategoryActivity.this,QuizActivity.class);
                intentTechnology.putExtra("Category", catAnimals.getText().toString());
                startActivity(intentTechnology);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(CategoryActivity.this, OpeningActivity.class));
    }
}