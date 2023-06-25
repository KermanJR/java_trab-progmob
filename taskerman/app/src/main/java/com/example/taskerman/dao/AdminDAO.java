package com.example.taskerman.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.taskerman.entities.Admin;

@Dao
public interface AdminDAO {
    @Insert
    void insertAdmin(Admin admin);

    @Query("SELECT * FROM admins WHERE username = :username AND password = :password")
    Admin authenticate(String username, String password);
}
