package com.example.quizapp.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAccountActivity extends AppCompatActivity {
    private Button createAcctButton;
    private FirebaseAuth firebaseAuth;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //Firestore connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText emailEditText;
    private EditText passwordEditText;
    private ProgressBar progressBar;
    private EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firebaseAuth = FirebaseAuth.getInstance();

        createAcctButton = findViewById(R.id.create_acct_button);
        progressBar = findViewById(R.id.create_acct_progress);
        emailEditText = findViewById(R.id.email_account);
        passwordEditText = findViewById(R.id.password_account);
        userNameEditText = findViewById(R.id.username_account);

        createAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration();
            }
        });
    }

    private void Registration()
    {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String username = userNameEditText.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            String emptyField = getString(R.string.empty_fields);
            Toast.makeText(CreateAccountActivity.this, emptyField,
                    Toast.LENGTH_SHORT).show();
        }
        else if(!email.matches(emailPattern))
        {
            String validEmail = getString(R.string.valid_email);
            emailEditText.setError(validEmail);
        }
        else if(password.length() < 6) {
            String validPassword = getString(R.string.valid_password);
            passwordEditText.setError(validPassword);
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Bundle b = ActivityOptions.makeSceneTransitionAnimation(CreateAccountActivity.this).toBundle();
                        Intent i = new Intent(CreateAccountActivity.this, CategoryActivity.class);
                        startActivity(i, b);

                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else{
                        String invalidRegistration = getString(R.string.server_error);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(CreateAccountActivity.this, invalidRegistration, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

