package com.example.databaseneco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvSecondName, tvEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }

    private void init(){
        mAuth = FirebaseAuth.getInstance();
        tvName = findViewById(R.id.tvName);
        tvSecondName = findViewById(R.id.tvSecondName);
        tvEmail = findViewById(R.id.tvEmail);
    }

    private void getIntentMain(){
        Intent i = getIntent();
        if (i != null){
            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvSecondName.setText(i.getStringExtra(Constant.USER_SECOND_NAME));
            tvEmail.setText(i.getStringExtra(Constant.USER_EMAIL));
        }
    }

    public void onClickSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}
