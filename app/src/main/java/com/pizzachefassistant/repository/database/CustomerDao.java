package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Customer;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface CustomerDao {
    @Insert(onConflict = REPLACE)
    void insert(Customer customer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Customer> customers);

    @Query("SELECT * FROM Customer WHERE id = :id")
    LiveData<Customer> load(int id);

    @Query("SELECT * FROM Customer")
    LiveData<List<Customer>> loadAll();

    @Update
    void updateCustomers(Customer... customers);
}