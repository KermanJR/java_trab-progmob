package com.example.taskerman.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskerman.dao.RegularUserDAO;
import com.example.taskerman.dao.TaskDAO;
import com.example.taskerman.entities.Admin;
import com.example.taskerman.entities.RegularUser;
import com.example.taskerman.entities.Task;

@Database(entities = {RegularUser.class, Admin.class, Task.class}, version = 5)
public abstract class LocalDatabase extends RoomDatabase {
    private static LocalDatabase INSTANCE;
    public static LocalDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    LocalDatabase.class, "DbTeste").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return INSTANCE;
    }


    public abstract RegularUserDAO regularUserModel();
    public abstract TaskDAO taskModel();

}