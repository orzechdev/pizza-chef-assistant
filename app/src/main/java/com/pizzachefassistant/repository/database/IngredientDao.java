package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Ingredient;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class IngredientDao {
    @Insert(onConflict = REPLACE)
    public abstract void insert(Ingredient ingredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<Ingredient> ingredients);

    @Query("SELECT * FROM `Ingredient` WHERE id = :id")
    public abstract LiveData<Ingredient> load(int id);

    @Query("SELECT * FROM `Ingredient` WHERE id = :id")
    public abstract Ingredient loadNow(int id);

    @Query("SELECT * FROM `Ingredient`")
    public abstract LiveData<List<Ingredient>> loadAll();

    @Query("UPDATE `Ingredient` SET amount = :amount WHERE id = :id")
    public abstract void updateIngredientStockAmount(int id, int amount);

    @Update
    public abstract void updateIngredients(Ingredient... ingredients);

    @Query("DELETE FROM `Ingredient`")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<Ingredient> ingredients) {
        deleteAll();
        insertAll(ingredients);
    }
}