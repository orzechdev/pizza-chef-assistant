package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String status;
    public String time;
    public float price;

    @Ignore
    public Order(String status, String time, float price){
        this.status = status;
        this.time = time;
        this.price = price;
    }
    public Order(int id, String status, String time, float price){
        this.id = id;
        this.status = status;
        this.time = time;
        this.price = price;
    }
}
