package com.pizzachefassistant.repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Time;

@Entity(foreignKeys = @ForeignKey(entity = Customer.class, parentColumns = "customerID", childColumns = "customerID_FK"))
public class Order {
    @PrimaryKey(autoGenerate = true)
    public final int orderID;
    public final String status;
    public final Time time;
    public final float price;

    public Order(int orderID, String status, Time time, float price){
        this.orderID = orderID;
        this.status = status;
        this.time = time;
        this.price = price;
    }
}
