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
    public boolean isDone;
    public String timeToFinish;

    @Ignore
    public Order(boolean isDone, String timeToFinish){
        this.isDone = isDone;
        this.timeToFinish = timeToFinish;
    }
    public Order(int id, boolean isDone, String timeToFinish){
        this.id = id;
        this.isDone = isDone;
        this.timeToFinish = timeToFinish;
    }
}
