package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Warehouse;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class WarehouseDao {
    @Insert(onConflict = REPLACE)
    public abstract void insert(Warehouse warehouse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<Warehouse> warehouses);

    @Query("SELECT * FROM `Warehouse` WHERE warehouseIngredientID = :warehouseIngredientID")
    public abstract LiveData<Warehouse> load(int warehouseIngredientID);

    @Query("SELECT * FROM `Warehouse`")
    public abstract LiveData<List<Warehouse>> loadAll();

    @Update
    public abstract void updateWarehouses(Warehouse... warehouses);

    @Query("DELETE FROM `Warehouse`")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<Warehouse> warehouses) {
        deleteAll();
        insertAll(warehouses);
    }
}