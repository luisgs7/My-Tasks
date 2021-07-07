package com.example.mytasks.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mytasks.R;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }
}