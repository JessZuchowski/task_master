package com.jz.taskmaster;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskLayoutAdapter extends RecyclerView.Adapter<TaskLayoutAdapter.TaskHolder> {

    public static class TaskHolder extends RecyclerView.ViewHolder {
        public TextView textTitle;
        public TextView textDescription;
        public TextView textState;

        public TaskHolder (@NonNull View itemView) {
            super (itemView);

            this.textTitle = itemView.findViewById(R.id.text_title);
            this.textDescription = itemView.findViewById(R.id.text_description);
            this.textState = itemView.findViewById(R.id.text_state);
        }

        public void setTask(ProjectTask projectTask) {
            this.textTitle.setText(projectTask.getTitle());
            this.textDescription.setText(projectTask.getDescription());
            this.textState.setText(projectTask.getState());
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

    public void setProjectTasks(ArrayList<ProjectTask> projectTasks) {
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
        holder.setTask(projectTask);

    }

    @Override
    public int getItemCount() {
        return projectTasks.size();
    }
}
