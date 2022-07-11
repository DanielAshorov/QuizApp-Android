package com.example.quizapp.helpers;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserHelper {

    FirebaseFirestore db;

    public void setUserFirstTime(String userName, String email)
    {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("animals", 1);
        userInfo.put("food", 1);
        userInfo.put("movies", 1);
        userInfo.put("music", 1);
        userInfo.put("sport", 1);
        userInfo.put("score", 0);
        userInfo.put("userName", userName);
        CollectionReference usersDB = db.collection("Users");
        usersDB.document(email).set(userInfo);
    }





}
