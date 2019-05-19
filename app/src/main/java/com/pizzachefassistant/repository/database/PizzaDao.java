package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class PizzaDao {
    @Insert(onConflict = REPLACE)
    public abstract long insert(Pizza pizza);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<Pizza> pizzas);

    @Query("SELECT * FROM Pizza WHERE id = :id")
    public abstract LiveData<Pizza> load(int id);

    @Query("SELECT * FROM Pizza")
    public abstract LiveData<List<Pizza>> loadAll();

    @Update
    public abstract void updatePizzas(Pizza... pizzas);

    @Query("DELETE FROM `Pizza`")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<Pizza> pizzas) {
        deleteAll();
        insertAll(pizzas);
    }
}