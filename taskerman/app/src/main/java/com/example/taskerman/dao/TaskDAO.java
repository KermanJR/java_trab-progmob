package com.example.taskerman.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskerman.entities.Task;
import java.util.List;

@Dao
public interface TaskDAO {
    @Query("SELECT * FROM Task WHERE taskId = :id LIMIT 1")
    Task getTask(int id);

    @Insert
    void addTask(Task... task);

    @Delete
    void removeTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();
}
