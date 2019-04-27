package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    public final String address;
    public final String phoneNumber;


    public Customer(int id, String address, String phoneNumber){
        this.id = id;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}

