package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Ingredient;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface IngredientDao {
    @Insert(onConflict = REPLACE)
    void insert(Ingredient ingredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Ingredient> ingredients);

    @Query("SELECT * FROM `Order` WHERE id = :id")
    LiveData<Ingredient> load(int id);

    @Query("SELECT * FROM `Order`")
    LiveData<List<Ingredient>> loadAll();

    @Update
    void updateIngredients(Ingredient... ingredients);
}