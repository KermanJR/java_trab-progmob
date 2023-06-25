package com.example.taskerman.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int taskId;
    public int userId;
    private String name;
    private String deadline;
    private String priority;
    private boolean completed;

    private String status;

    public Task(String name, String deadline, String priority, String status) {
        this.name = name;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = false; // Por padrão, a tarefa não está concluída;
        this.status = status;
    }

    // Getters e setters

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id: " + taskId + "\n" +
                "Nome: " + name + "\n" +
                "Prazo: " + deadline + "\n" +
                "Prioridade: " + priority + "\n" +
                "Status: " + status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId){
        this.taskId = taskId;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
