package com.example.quizapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * from questions_table WHERE category = :category and language = :language")
    LiveData<List<Questions>> getQuestionsByCategory(String category, String language);

    @Insert
    void insert(Questions questions);

}
