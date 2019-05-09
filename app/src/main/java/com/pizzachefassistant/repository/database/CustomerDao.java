package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Customer;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class CustomerDao {
    @Insert(onConflict = REPLACE)
    public abstract void insert(Customer customer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<Customer> customers);

    @Query("SELECT * FROM Customer WHERE id = :id")
    public abstract LiveData<Customer> load(int id);

    @Query("SELECT * FROM Customer")
    public abstract LiveData<List<Customer>> loadAll();

    @Update
    public abstract void updateCustomers(Customer... customers);

    @Query("DELETE FROM Customer")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<Customer> customers) {
        deleteAll();
        insertAll(customers);
    }
}