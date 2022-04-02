package com.example.databaseneco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText edLoginEmail, edLoginPassword;
    private FirebaseAuth mAuth;
    private Button btnSignUp, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null){
            Intent i = new Intent(this, ShowActivity.class);
            startActivity(i);
            Toast.makeText(this, "User is not null", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "User is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        edLoginEmail = findViewById(R.id.edLoginEmail);
        edLoginPassword = findViewById(R.id.edLoginPassword);
        mAuth = FirebaseAuth.getInstance();
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    public void onClickSignUp(View view) {
        if (!TextUtils.isEmpty(edLoginEmail.getText().toString()) && !TextUtils.isEmpty(edLoginPassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(edLoginEmail.getText().toString(), edLoginPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(LoginActivity.this, ShowActivity.class);
                                startActivity(i);
                                Toast.makeText(LoginActivity.this, "User signUp successfully ", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, "User signUp failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(LoginActivity.this, "Please enter an email and password", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSignIn(View view) {
        if (!TextUtils.isEmpty(edLoginEmail.getText().toString()) && !TextUtils.isEmpty(edLoginPassword.getText().toString())){
            mAuth.signInWithEmailAndPassword(edLoginEmail.getText().toString(), edLoginPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent i = new Intent(LoginActivity.this, ShowActivity.class);
                                startActivity(i);
                                Toast.makeText(LoginActivity.this, "User signIn successfully ", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoginActivity.this, "User signIn failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void senEmailVer(){
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @SuppressLint("ShowToast")
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Check your email for verification", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Send email failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}