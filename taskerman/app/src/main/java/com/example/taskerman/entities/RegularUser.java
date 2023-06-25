package com.example.taskerman.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "regular_users")
public class RegularUser {
    @PrimaryKey(autoGenerate = true)
    private int id;
    public int taskId;

    String username;
    String password;
    String email;

    public RegularUser(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public  RegularUser(){}

    public int getTaskId() {
        return taskId;
    }

    // getters e setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
