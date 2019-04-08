package com.pizzachefassistant.repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public final int userID;
    public final String login;
    protected final String password;
    public final String email;

    public User(int userID, String login, String password, String email){
        this.userID = userID;
        this.login = login;
        this.password = password;
        this.email = email;
    }

}
