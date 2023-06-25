package com.example.taskerman.views;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taskerman.database.LocalDatabase;
import com.example.taskerman.databinding.ActivityTaskViewBinding;
import com.example.taskerman.entities.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TaskView extends AppCompatActivity {
    private LocalDatabase db;
    private ActivityTaskViewBinding binding;
    private Task dbTask;
    private int dbTaskId;
    private String[] items;
    private Spinner spnPriorities;
    private ArrayAdapter prioritiesAdapter;

    private TextView textViewSelectedDate;
    private Button buttonSelectDate;
    private Calendar selectedDate;
    
    private String status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTaskViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LocalDatabase.getDatabase(getApplicationContext());
        CheckBox taskCompletedCheckBox = binding.taskCompletedCheckBox;


        spnPriorities = binding.editTextTaskPriority;
        dbTaskId = getIntent().getIntExtra(
                "TAREFA_SELECIONADA_ID", -1);

        textViewSelectedDate = binding.textViewSelectedDate;
        buttonSelectDate = binding.buttonSelectDate;
        CheckBox checkBox1 = binding.taskCompletedCheckBox;
        CheckBox checkBox2 = binding.taskCompletedCheckBox2;

        buttonSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = "Concluida";
                } 
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    status = "Pendente";
                }
            }
        });

        binding.buttonSaveTask.setOnClickListener(v -> saveTask());
        binding.buttonDeleteTask.setOnClickListener(v -> deleteTask());

    }
    
    

    private void showDatePicker() {
        final Calendar currentDate = Calendar.getInstance();
        int year = currentDate.get(Calendar.YEAR);
        int month = currentDate.get(Calendar.MONTH);
        int day = currentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDate = Calendar.getInstance();
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, monthOfYear);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(selectedDate.getTime());
                textViewSelectedDate.setText(formattedDate);
            }
        }, year, month, day);

        datePickerDialog.show();
    }
    


    @Override
    protected void onResume(){
        super.onResume();
        if(dbTaskId>=0){
            preencheTarefa();
        }
        preenchePrioridades();
    }


    private void preencheTarefa() {
        dbTask = db.taskModel().getTask(dbTaskId);
        binding.editTextTaskName.setText(dbTask.getName());
        binding.textViewSelectedDate.setText(dbTask.getDeadline());
        binding.taskCompletedCheckBox.setChecked(dbTask.isCompleted());
        
    }

    private void preenchePrioridades(){
        items = new String[]{"Alta", "Média", "Baixa"};
        prioritiesAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        spnPriorities.setAdapter(prioritiesAdapter);
    }

    private void saveTask() {
        String taskName = binding.editTextTaskName.getText().toString();
        String taskDeadLine = binding.textViewSelectedDate.getText().toString();


        String priority = "";

        if(spnPriorities.getSelectedItem() != null){
            priority = spnPriorities.getSelectedItem().toString();
        }

        if (taskName.isEmpty()) {
            Toast.makeText(this, "Digite um nome de tarefa válido", Toast.LENGTH_SHORT).show();
            return;
        }

        Task newTask = new Task(taskName, taskDeadLine, priority, status);

        if(dbTask != null){
            newTask.setTaskId(dbTaskId);
            db.taskModel().updateTask(newTask);
            Toast.makeText(this, "Tarefa atualizada com sucesso", Toast.LENGTH_SHORT).show();
        }else{
            db.taskModel().addTask(newTask);
            Toast.makeText(this, "Tarefa cadastrada com sucesso", Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    private void deleteTask() {
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de tarefa")
                .setMessage("Deseja excluir essa tarefa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void excluir() {
        db.taskModel().removeTask(dbTask);
        Toast.makeText(this, "Tarefa excluída com sucesso.", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void voltar(View view) {
        finish();
    }

}
