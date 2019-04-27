package com.pizzachefassistant.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.pizzachefassistant.repository.database.utils.DateConverter;
import com.pizzachefassistant.repository.model.Customer;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.Pizza;

@Database(entities = {Pizza.class, Order.class, Customer.class}, version = 1)
@TypeConverters({DateConverter.class})
public abstract class MainDatabase extends RoomDatabase {
    public abstract PizzaDao pizzaDao();
    public abstract OrderDao orderDao();
    public abstract CustomerDao customerDao();
}