package com.example.mytasks.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mytasks.R;
import com.example.mytasks.adapter.TaskAdapter;
import com.example.mytasks.helper.DbTaskHelper;
import com.example.mytasks.helper.RecyclerItemClickListener;
import com.example.mytasks.helper.TaskDAO;
import com.example.mytasks.model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList = new ArrayList<>();
    private Task taskSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.float_button);
        recyclerView = findViewById(R.id.recyclerTask);

       /*  DbTaskHelper db = new DbTaskHelper(getApplicationContext());

        ContentValues cv = new ContentValues();
        cv.put("nome", "teste");

        db.getWritableDatabase().insert("task", null, cv);

        */


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Task taskSelected = taskList.get(position);

                                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                                intent.putExtra("taskSelected", taskSelected);

                                startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                taskSelected = taskList.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                                dialog.setTitle(R.string.delete_task);
                                dialog.setMessage("Deseja excluir a Task: " + taskSelected.getNameTask() + "?");

                                dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        TaskDAO taskDAO = new TaskDAO(getApplicationContext());

                                        if(taskDAO.delete(taskSelected)){
                                            loadingListTask();
                                            Toast.makeText(getApplicationContext(), R.string.task_delete, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getApplicationContext(), R.string.task_delete_error, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                                dialog.setNegativeButton(getString(R.string.no), null);

                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        loadingListTask();
    }

    public void loadingListTask(){

        TaskDAO taskDAO = new TaskDAO(getApplicationContext());
        taskList = taskDAO.list();

        taskAdapter = new TaskAdapter(taskList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

        recyclerView.setAdapter(taskAdapter);
    }

}