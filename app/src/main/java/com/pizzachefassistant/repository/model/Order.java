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

@Entity(
        foreignKeys = @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "userId"),
        indices = {@Index("userId")}
)
public class Order {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String status;
    public Date time;
    public float price;
    public int userId;

    @Ignore
    public Order(String status, Date time, float price, int userId){
        this.status = status;
        this.time = time;
        this.price = price;
        this.userId = userId;
    }
    public Order(int id, String status, Date time, float price, int userId){
        this.id = id;
        this.status = status;
        this.time = time;
        this.price = price;
        this.userId = userId;
    }
}
