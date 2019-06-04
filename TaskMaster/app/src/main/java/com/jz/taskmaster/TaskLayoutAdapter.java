package com.jz.taskmaster;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskLayoutAdapter extends RecyclerView.Adapter<TaskLayoutAdapter.TaskHolder> {

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TaskHolder extends RecyclerView.ViewHolder {
        public TextView textAvailable;
        public TextView textAssigned;
        public TextView textAccepted;
        public TextView textFinished;

        public TaskHolder (@NonNull View itemView) {
            super (itemView);

            this.textAvailable = itemView.findViewById(R.id.text_available);
            this.textAssigned = itemView.findViewById(R.id.text_assigned);
            this.textAccepted = itemView.findViewById(R.id.text_accepted);
            this.textFinished = itemView.findViewById(R.id.text_finished);
        }

        public void setTask(Task task) {

        }
    }
}
