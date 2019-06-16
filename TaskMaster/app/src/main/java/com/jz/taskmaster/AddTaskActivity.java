package com.jz.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class AddTaskActivity extends AppCompatActivity {

    //firebase
    FirebaseFirestore database;
    FirebaseUser user;
    Context context;

    //add tasks
    EditText title;
    EditText description;
    EditText available;
    EditText assigned;
    EditText accepted;
    EditText accomplished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        //get Firestore instance
        database = FirebaseFirestore.getInstance();
        //set Firestore settings
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        database.setFirestoreSettings(settings);

        context = this;

        //user profile
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
        }
        setUI();
    }

    //display user info
    private void setUI() {
        TextView text1 = findViewById(R.id.text_content);
        if (user != null) {
            text1.setText("Add Tasks: " + user.getDisplayName());
        }
        else {
            text1.setText("");
        }
    }

    //add a new task
    public void onCreateTaskClick(View view) {
        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        available = findViewById(R.id.task_available);
        assigned = findViewById(R.id.task_assigned);
        accepted = findViewById(R.id.task_accepted);
        accomplished = findViewById(R.id.task_accomplished);


        ProjectTask projectTask = new ProjectTask();
        projectTask.setTitle("Title: " + title.getText().toString());
        projectTask.setDescription("Description: " + description.getText().toString());
        projectTask.setAvailable("Available? " + available.getText().toString());
        projectTask.setAssigned("Assigned? " + assigned.getText().toString());
        projectTask.setAccepted("Accepted? " + accepted.getText().toString());
        projectTask.setAccomplished("Accomplished? " + accomplished.getText().toString());


        database.collection("projectTasks")
                .add(projectTask)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Task", "Successfully Added" + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Task", "Log Has Failed", e);
                    }
                });
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
