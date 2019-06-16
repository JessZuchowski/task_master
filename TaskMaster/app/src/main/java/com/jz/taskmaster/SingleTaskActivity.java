package com.jz.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SingleTaskActivity extends AppCompatActivity {

    //firebase
    FirebaseFirestore database;
    FirebaseUser user;
    String projectTaskId;
    ProjectTask singleTask;

    //edit single task
    public TextView title;
    public TextView description;
    public TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_task);

        //get Firestore instance
        database = FirebaseFirestore.getInstance();

        //user profile
        user = FirebaseAuth.getInstance().getCurrentUser();
        setUI();

        //id from database
        Intent intent = getIntent();
        projectTaskId = intent.getStringExtra("projectTaskId");

        this.title = findViewById(R.id.single_task_title);
        this.description = findViewById(R.id.single_task_description);
        this.state = findViewById(R.id.single_task_state);

    }

    //display user info
    private void setUI() {
        TextView text = findViewById(R.id.text_content);
        if (user != null) {
            text.setText("Single Task View: " + user.getDisplayName());
        }
        else {
            text.setText("");
        }
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
