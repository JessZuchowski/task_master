package com.jz.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MyTaskActivity extends AppCompatActivity {

    FirebaseFirestore database;
    FirebaseUser user;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TaskLayoutAdapter adapter;

    public List<ProjectTask> projectTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);

        database = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        setUI();

        projectTasks = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.task_entry);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TaskLayoutAdapter(projectTasks);
        recyclerView.setAdapter(adapter);
    }

    //get all tasks from database
    public void onViewAllTasksClick(View view) {
        database.collection("projectTasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot snap = task.getResult();
                            List<ProjectTask> projectTasks = new ArrayList<>();
                            for (DocumentSnapshot document : snap.getDocuments()) {
                                Log.d("Task", document.getId() + " " + document.getData());
                                ProjectTask pt = document.toObject(ProjectTask.class);

                                pt.withProjectTaskId(document.getId());
                                String projectTaskId = document.getId();
                                projectTasks.add(pt);
                            }
                            adapter.setProjectTasks(projectTasks);
                        }
                        else{
                            Log.w("Task", "Error Getting Tasks", task.getException());
                        }
                    }
                });
    }

    public void onHomeButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setUI() {
        TextView text = findViewById(R.id.text_content);
        if (user != null) {
            text.setText("My Tasks: " + user.getDisplayName());
        }
        else {
            text.setText("");
        }
    }
}
