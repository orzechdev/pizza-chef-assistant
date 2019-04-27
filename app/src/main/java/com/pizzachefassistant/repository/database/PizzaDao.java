package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PizzaDao {
    @Insert(onConflict = REPLACE)
    void insert(Pizza pizza);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Pizza> pizzas);

    @Query("SELECT * FROM Pizza WHERE id = :id")
    LiveData<Pizza> load(int id);

    @Query("SELECT * FROM Pizza")
    LiveData<List<Pizza>> loadAll();

    @Update
    void updatePizzas(Pizza... pizzas);
}