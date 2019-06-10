package com.jz.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SingleTaskActivity extends AppCompatActivity {

    //firebase
    FirebaseFirestore database;
    FirebaseUser user;

    //edit task
    EditText title;
    EditText description;
    EditText state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);
    }
}
