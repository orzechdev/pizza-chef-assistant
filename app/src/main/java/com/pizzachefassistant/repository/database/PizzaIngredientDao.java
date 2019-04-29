package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.PizzaIngredient;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PizzaIngredientDao {
    @Insert(onConflict = REPLACE)
    void insert(PizzaIngredient pizzaIngredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PizzaIngredient> pizzaIngredients);

    @Query("SELECT * FROM `Order` WHERE id = :id")
    LiveData<PizzaIngredient> load(int id);

    @Query("SELECT * FROM `Order`")
    LiveData<List<PizzaIngredient>> loadAll();

    @Update
    void updatePizzaIngredients(PizzaIngredient... pizzaIngredients);
}