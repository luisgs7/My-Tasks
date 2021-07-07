package com.example.mytasks.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHoler> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> list) {
        this.taskList = list;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.MyViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {

        public MyViewHoler(View itemView) {
            super(itemView);
        }
    }
}
