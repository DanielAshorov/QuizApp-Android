package com.example.quizapp;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;
public class QuestionRepository {


    private QDao mQDao;
    private LiveData<List<Questions>> mAllQuestions;


    public QuestionRepository(Application application, String language){
        QuestionRoomDatabase db = QuestionRoomDatabase.getInstance(application);
        mQDao = db.questionDao();
    }

    //with categories

    public LiveData<List<Questions>> getQuestions(String category, String language){

        mAllQuestions = mQDao.getQuestionsByCategory(category,language);

        return mAllQuestions;
    }

}
