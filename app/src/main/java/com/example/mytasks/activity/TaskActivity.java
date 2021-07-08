package com.example.mytasks.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mytasks.R;
import com.example.mytasks.helper.TaskDAO;
import com.example.mytasks.model.Task;
import com.google.android.material.textfield.TextInputEditText;

public class TaskActivity extends AppCompatActivity {

    private TextInputEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editText = findViewById(R.id.edit_task);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_save:
                TaskDAO taskDAO = new TaskDAO(getApplicationContext());

                String nameTask = editText.getText().toString();

                if(!nameTask.isEmpty() ){
                    Task task = new Task();
                    task.setNameTask(nameTask);
                    taskDAO.save(task);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), R.string.task_validation, Toast.LENGTH_LONG).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}