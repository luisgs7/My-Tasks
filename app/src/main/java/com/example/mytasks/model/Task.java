package com.example.mytasks.model;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String nameTask;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
