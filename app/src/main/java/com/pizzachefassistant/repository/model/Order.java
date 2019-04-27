package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;

@Entity(foreignKeys = @ForeignKey(entity = Customer.class, parentColumns = "id", childColumns = "userId"))
public class Order {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    public final String status;
    public final Date time;
    public final float price;
    public final int userId;

    public Order(int id, String status, Date time, float price, int userId){
        this.id = id;
        this.status = status;
        this.time = time;
        this.price = price;
        this.userId = userId;
    }
}
