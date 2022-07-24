package com.example.quizapp.BL;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizapp.DAL.QuestionRepository;
import com.example.quizapp.R;

import java.util.List;

public class QuestionViewModel  extends AndroidViewModel {

    private QuestionRepository mRepository;

    private LiveData<List<Questions>> mAllQuestions;

    public QuestionViewModel(Application application){

        super(application);
        String language = application.getString(R.string.language);
        mRepository = new QuestionRepository(application, language);
    }

    public LiveData<List<Questions>> getAllQuestionByCategory(String category, String language) {

        mAllQuestions = mRepository.getQuestions(category,language);
        return mAllQuestions;
    }
}


