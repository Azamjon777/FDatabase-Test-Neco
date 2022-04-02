package com.example.databaseneco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edName, edNameSecond, edEmail;
    private DatabaseReference mDatabase;
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init(){
        edName = findViewById(R.id.edName);
        edNameSecond = findViewById(R.id.edNameSecond);
        edEmail = findViewById(R.id.edLoginEmail);

        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClickSave(View view) {
        String id = mDatabase.getKey();
        String name = edName.getText().toString();
        String secondName = edNameSecond.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id, name, secondName, email);

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(secondName) && !TextUtils.isEmpty(email)){
            mDatabase.push().setValue(newUser);
            Toast.makeText(this, "Аккаунт создан", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, ReadActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRead(View view) {
        Intent i = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(i);
    }
}