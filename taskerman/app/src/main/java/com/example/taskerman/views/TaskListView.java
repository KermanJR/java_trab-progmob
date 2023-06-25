package com.example.taskerman.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskerman.R;
import com.example.taskerman.database.LocalDatabase;
import com.example.taskerman.databinding.ActivityTaskListViewBinding;
import com.example.taskerman.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskListView extends AppCompatActivity {
    private ListView listViewTasks;
    private ArrayAdapter<Task> taskAdapter;
    private LocalDatabase db;
    private List<Task> taskList;
    private Intent edtIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.taskerman.databinding.ActivityTaskListViewBinding binding = ActivityTaskListViewBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        db = LocalDatabase.getDatabase(getApplicationContext());
        listViewTasks = binding.listTask;

        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(taskAdapter);

        binding.btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskListView.this, TaskView.class));
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        edtIntent = new Intent(this, TaskView.class);
        preencheTarefas();
    }
    private void preencheTarefas() {
        taskList = db.taskModel().getAllTasks();
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);

        listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int position, long id) {
                Task tarefaSelecionada = taskList.get(position);
                edtIntent.putExtra("TAREFA_SELECIONADA_ID", tarefaSelecionada.getTaskId());
                startActivity(edtIntent);
            }
        });
    }


}
