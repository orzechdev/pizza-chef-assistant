package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String address;
    public String phoneNumber;

    @Ignore
    public Customer(String address, String phoneNumber){
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public Customer(int id, String address, String phoneNumber){
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}

