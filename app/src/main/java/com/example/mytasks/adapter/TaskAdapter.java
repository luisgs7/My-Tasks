package com.example.mytasks.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytasks.R;
import com.example.mytasks.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHoler> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> list) {
        this.taskList = list;
    }

    @Override
    public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.task_adapter, parent, false);

        return new MyViewHoler(itemLista);
    }

    @Override
    public void onBindViewHolder(TaskAdapter.MyViewHoler holder, int position) {
        Task task = taskList.get(position);
        holder.task.setText(task.getNameTask());
    }

    @Override
    public int getItemCount() {
        return this.taskList.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder {

        TextView task;

        public MyViewHoler(View itemView) {
            super(itemView);

            task = itemView.findViewById(R.id.text_task);
        }
    }
}
