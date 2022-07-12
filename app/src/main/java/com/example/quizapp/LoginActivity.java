package com.example.quizapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.model.UserDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button createAcctButton;
    private EditText inputEmail, inputPassword;
    FirebaseAuth authentication;
    private ProgressDialog progressDialog;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        loginButton = findViewById(R.id.email_sign_in_button);

        createAcctButton = findViewById(R.id.create_acct_button_login);

        createAcctButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputEmail= findViewById(R.id.email);
                inputPassword = findViewById(R.id.password);
                authentication = FirebaseAuth.getInstance();
                Authentication();
            }
        });

    }

    private void Authentication()
    {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if(email.isEmpty()||password.isEmpty())
        {
            String emptyField = getString(R.string.empty_fields);
            Toast.makeText(LoginActivity.this, emptyField,
                    Toast.LENGTH_SHORT).show();
        }
        if(!email.matches(emailPattern))
        {
            String validEmail = getString(R.string.valid_email);
            inputEmail.setError(validEmail);
        }
        else if(password.length() < 6) {
            String validPassword = getString(R.string.valid_password);
            inputPassword.setError(validPassword);
        }
        else
        {
            String waitLogin = getString(R.string.wait_login);
            progressDialog.setMessage(waitLogin);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            authentication.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        final UserDetails[] data = new UserDetails[1];
                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        db.collection("Users").document(email).get().
                                addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        data[0] = documentSnapshot.toObject(UserDetails.class);

                                        progressDialog.cancel();
                                        Intent main = new Intent(LoginActivity.this,  CategoryActivity.class);
                                        main.putExtra("userDetails", data[0]);
                                        startActivity(main);
                                    }
                                });
                    }
                    else
                    {
                        if(task.getException().getMessage().contains("password"))
                        {
                            String correctPassword = getString(R.string.correct_password);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, correctPassword, Toast.LENGTH_SHORT).show();

                        }
                        else if(task.getException().getMessage().contains("user"))
                        {
                            String correctEmail = getString(R.string.correct_email_address);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, correctEmail, Toast.LENGTH_SHORT).show();

                        }
                        else {
                            String serverError = getString(R.string.server_error);
                            progressDialog.cancel();
                            Toast.makeText(LoginActivity.this, serverError, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }
}


