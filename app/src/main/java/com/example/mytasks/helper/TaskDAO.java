package com.example.mytasks.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mytasks.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDao{

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TaskDAO(Context context) {
        DbTaskHelper db = new DbTaskHelper(context);

        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getNameTask());

        try {
            write.insert(DbTaskHelper.TABLE_TASK, null, cv);
            Log.i("INFO","Sucess the save task");
        }catch (Exception e){
            Log.i("INFO","Erro the save task " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Task task) {

        ContentValues cv = new ContentValues();
        cv.put("name", task.getNameTask());


        try {

            String[] args = {task.getId().toString()};
            write.update(DbTaskHelper.TABLE_TASK, cv, "id=?", args);
            Log.i("INFO","Sucess the update task");
        }catch (Exception e){
            Log.i("INFO","Erro the update task " + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Task task) {
        return false;
    }

    @Override
    public List<Task> list() {

        List<Task> taskList = new ArrayList<>();

        String sql = "SELECT * FROM " + DbTaskHelper.TABLE_TASK + " ;";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()){

            Task task = new Task();

            Long id = c.getLong(c.getColumnIndex("id"));
            String name= c.getString(c.getColumnIndex("name"));

            task.setId(id);
            task.setNameTask(name);

            taskList.add(task);
        }

        return taskList;
    }
}










