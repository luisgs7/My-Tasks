package com.example.mytasks.model;

import java.io.Serializable;

public class Task implements Serializable {

    private Long id;
    private String nameTask;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }
}
