package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    public final String login;
    protected final String password;
    public final String email;

    public User(int id, String login, String password, String email){
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

}
