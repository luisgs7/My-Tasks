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
    private Task taskAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editText = findViewById(R.id.edit_task);

        //Update
        taskAtual = (Task) getIntent().getSerializableExtra("taskSelected");
        if (taskAtual != null){
            editText.setText(taskAtual.getNameTask());
        }

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

                if(taskAtual != null){
                    String nameTask = editText.getText().toString();
                    if(!nameTask.isEmpty()){
                        Task task = new Task();
                        task.setNameTask(nameTask);
                        task.setId(taskAtual.getId());

                        if(taskDAO.update(task)){
                            Toast.makeText(getApplicationContext(), R.string.task_update, Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), R.string.task_update_error, Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{


                    String nameTask = editText.getText().toString();

                    if(!nameTask.isEmpty() ){
                        Task task = new Task();
                        task.setNameTask(nameTask);

                        if(taskDAO.save(task)){
                            Toast.makeText(getApplicationContext(), R.string.task_save, Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), R.string.task_validation, Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}