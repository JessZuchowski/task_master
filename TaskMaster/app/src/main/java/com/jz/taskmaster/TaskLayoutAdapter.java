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

        public void setTask(Task task) {
            this.textTitle.setText(task.getTitle());
            this.textDescription.setText(task.getDescription());
            this.textState.setText(task.getState());
        }
    }

    private List<Task> tasks;

    public TaskLayoutAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
        this.notifyItemRemoved(index);
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
        Task task = tasks.get(position);
        holder.setTask(task);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
