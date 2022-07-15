package com.example.quizapp;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import android.util.Log;

@Database(entities = {Questions.class},version = 8)
abstract class QuestionRoomDatabase extends RoomDatabase {

    private static QuestionRoomDatabase INSTANCE;
    public abstract QuestionDao questionDao();

    public static synchronized QuestionRoomDatabase getInstance(final Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    QuestionRoomDatabase.class, "questions_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(RoomDBCallback)
                    .build();

            // or query a dummy select statement
            INSTANCE.query("select 1", null);
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback RoomDBCallback = new RoomDatabase.Callback() {


        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuestionDao questionDao;


        private PopulateDbAsyncTask(QuestionRoomDatabase db) {

            questionDao = db.questionDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }
    }
}



