package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Order;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class OrderDao {
    @Insert(onConflict = REPLACE)
    public abstract void insert(Order order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<Order> orders);

    @Query("SELECT * FROM `Order` WHERE id = :id")
    public abstract LiveData<Order> load(int id);

    @Query("SELECT * FROM `Order`")
    public abstract LiveData<List<Order>> loadAll();

    @Update
    public abstract void updateOrders(Order... orders);

    @Query("DELETE FROM `Order`")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<Order> orders) {
        deleteAll();
        insertAll(orders);
    }
}