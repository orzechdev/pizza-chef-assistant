package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Order;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderDao {
    @Insert(onConflict = REPLACE)
    void insert(Order order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Order> orders);

    @Query("SELECT * FROM `Order` WHERE id = :id")
    LiveData<Order> load(int id);

    @Query("SELECT * FROM `Order`")
    LiveData<List<Order>> loadAll();

    @Update
    void updateOrders(Order... orders);
}