package com.pizzachefassistant.repository.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.pizzachefassistant.repository.database.utils.DateConverter;
import com.pizzachefassistant.repository.model.Customer;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;
import com.pizzachefassistant.repository.model.Warehouse;

@Database(entities = {Pizza.class, Order.class, Customer.class, Ingredient.class, PizzaIngredient.class, Warehouse.class, OrderPizza.class}, version = 9)
@TypeConverters({DateConverter.class})
public abstract class MainDatabase extends RoomDatabase {
    public abstract PizzaDao pizzaDao();
    public abstract OrderDao orderDao();
    public abstract CustomerDao customerDao();
    public abstract IngredientDao ingredientDao();
    public abstract PizzaIngredientDao pizzaIngredientDao();
    public abstract WarehouseDao warehouseDao();
    public abstract OrderPizzaDao orderPizzaDao();
}