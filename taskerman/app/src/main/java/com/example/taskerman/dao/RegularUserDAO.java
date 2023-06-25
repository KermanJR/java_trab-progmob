package com.example.taskerman.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.taskerman.entities.RegularUser;

@Dao
public interface RegularUserDAO {
    @Insert
    void insertRegularUser(RegularUser regularUser);



    @Query("SELECT * FROM regular_users WHERE username = :username AND password = :password")
    RegularUser authenticate(String username, String password);
}
