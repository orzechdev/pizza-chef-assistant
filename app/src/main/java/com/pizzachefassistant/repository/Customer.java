package com.pizzachefassistant.repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Customer {
    @PrimaryKey(autoGenerate = true)
    public final int customerID;
    public final String address;
    public final String phoneNumber;


    public Customer(int customerID, String address, String phoneNumber){
        this.customerID = customerID;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

}

