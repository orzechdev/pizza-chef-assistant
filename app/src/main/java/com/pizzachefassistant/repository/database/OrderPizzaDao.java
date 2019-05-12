package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.OrderPizza;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class OrderPizzaDao {
    @Insert(onConflict = REPLACE)
    public abstract void insert(OrderPizza orderPizza);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<OrderPizza> orderPizzas);

    @Query("SELECT * FROM `OrderPizza` WHERE orderID_FK = :orderID_FK AND pizzaID_FK = :pizzaID_FK")
    public abstract LiveData<OrderPizza> load(int orderID_FK, int pizzaID_FK);

    @Query("SELECT * FROM `OrderPizza`")
    public abstract LiveData<List<OrderPizza>> loadAll();

    @Update
    public abstract void updateOrderPizzas(OrderPizza... orderPizzas);

    @Query("DELETE FROM `OrderPizza`")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<OrderPizza> orderPizzas) {
        deleteAll();
        insertAll(orderPizzas);
    }
}