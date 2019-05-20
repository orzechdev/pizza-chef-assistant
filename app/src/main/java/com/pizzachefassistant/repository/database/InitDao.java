package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class InitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllIngredient(List<Ingredient> values);
    @Query("DELETE FROM `Ingredient`")
    public abstract void deleteAllIngredient();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllPizza(List<Pizza> values);
    @Query("DELETE FROM `Pizza`")
    public abstract void deleteAllPizza();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllPizzaIngredient(List<PizzaIngredient> values);
    @Query("DELETE FROM `PizzaIngredient`")
    public abstract void deleteAllPizzaIngredient();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllOrder(List<Order> values);
    @Query("DELETE FROM `Order`")
    public abstract void deleteAllOrder();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAllOrderPizza(List<OrderPizza> values);
    @Query("DELETE FROM `OrderPizza`")
    public abstract void deleteAllOrderPizza();

    @Transaction
    public void deleteAndCreate(List<Ingredient> ingredients, List<Pizza> pizzas, List<PizzaIngredient> pizzaIngredients, List<Order> orders, List<OrderPizza> orderPizzas) {
        deleteAllOrderPizza();
        deleteAllOrder();
        deleteAllPizzaIngredient();
        deleteAllPizza();
        deleteAllIngredient();

        insertAllIngredient(ingredients);
        insertAllPizza(pizzas);
        insertAllPizzaIngredient(pizzaIngredients);
        insertAllOrder(orders);
        insertAllOrderPizza(orderPizzas);
    }
}