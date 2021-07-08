package com.example.mytasks.helper;

import com.example.mytasks.model.Task;

import java.util.List;

public interface ITaskDao {

    public boolean save(Task task);
    public boolean update(Task task);
    public boolean delete(Task task);
    public List<Task> list();
}
