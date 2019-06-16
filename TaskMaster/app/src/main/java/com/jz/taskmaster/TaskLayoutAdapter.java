package com.jz.taskmaster;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckedTextView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskLayoutAdapter extends RecyclerView.Adapter<TaskLayoutAdapter.TaskHolder> {

    public static class TaskHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textDescription;
        public TextView textAvailable;
        public TextView textAssigned;
        public TextView textAccepted;
        public  TextView textAccomplished;

        FirebaseFirestore database;

        public TaskHolder (@NonNull View itemView) {
            super (itemView);

            this.textTitle = itemView.findViewById(R.id.text_title);
            this.textDescription = itemView.findViewById(R.id.text_description);
            this.textAvailable = itemView.findViewById(R.id.text_available);
            this.textAssigned = itemView.findViewById(R.id.text_assigned);
            this.textAccepted = itemView.findViewById(R.id.text_accepted);
            this.textAccomplished = itemView.findViewById(R.id.text_accomplished);

            database = FirebaseFirestore.getInstance();
        }

        //set task, on click listener to get task from db by id
        public void setProjectTask(final ProjectTask projectTask) {
            this.textTitle.setText(projectTask.getTitle());
            this.textDescription.setText(projectTask.getDescription());
            this.textAvailable.setText(projectTask.getAvailable());
            this.textAssigned.setText(projectTask.getAssigned());
            this.textAccepted.setText(projectTask.getAccepted());
            this.textAccomplished.setText(projectTask.getAccomplished());


            final String projectTaskId = projectTask.getProjectTaskId();
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, SingleTaskActivity.class);
                    intent.putExtra("projectTaskId", projectTask.getProjectTaskId());
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<ProjectTask> projectTasks;

    public TaskLayoutAdapter(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
    }

    public void removeTask(int index) {
        this.projectTasks.remove(index);
        this.notifyItemRemoved(index);
    }

    public void setProjectTasks(List<ProjectTask> projectTasks) {
        this.projectTasks = projectTasks;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.task_view, parent, false);

        TaskHolder holder = new TaskHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        ProjectTask projectTask = projectTasks.get(position);
        holder.setProjectTask(projectTask);

    }

    @Override
    public int getItemCount() {
        return projectTasks.size();
    }
}