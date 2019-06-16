package com.pizzachefassistant.repository.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Transaction;

import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;

import java.util.List;

import java8.util.stream.StreamSupport;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public abstract class PizzaIngredientDao {
    @Insert(onConflict = REPLACE)
    public abstract void insert(PizzaIngredient pizzaIngredient);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertAll(List<PizzaIngredient> pizzaIngredients);

    @Query("SELECT * FROM `PizzaIngredient` WHERE pizzaID_FK = :pizzaID_FK AND ingredientID_FK = :ingredientID_FK")
    public abstract LiveData<PizzaIngredient> load(int pizzaID_FK, int ingredientID_FK);

    @Query("SELECT * FROM `PizzaIngredient` WHERE pizzaID_FK = :pizzaID_FK")
    public abstract LiveData<List<PizzaIngredient>> loadByPizzaId(int pizzaID_FK);

    @Query("SELECT * FROM `PizzaIngredient` WHERE pizzaID_FK = :pizzaID_FK")
    public abstract List<PizzaIngredient> loadByPizzaIdNow(int pizzaID_FK);

    @Query("SELECT * FROM `PizzaIngredient`")
    public abstract LiveData<List<PizzaIngredient>> loadAll();

    @Query("SELECT Ingredient.* FROM Ingredient INNER JOIN PizzaIngredient ON PizzaIngredient.ingredientID_FK = Ingredient.id WHERE pizzaID_FK = :pizzaID_FK")
    public abstract LiveData<List<Ingredient>> loadIngredientsByPizzaId(int pizzaID_FK);

    @Update
    public abstract void updatePizzaIngredients(PizzaIngredient... pizzaIngredients);

    @Query("DELETE FROM `PizzaIngredient`")
    public abstract void deleteAll();

    @Transaction
    public void deleteAndCreate(List<PizzaIngredient> pizzaIngredients) {
        deleteAll();
        insertAll(pizzaIngredients);
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertPizza(Pizza pizza);

    @Transaction
    public long insertPizzaTransact(Pizza pizza) {
        return insertPizza(pizza);
//        StreamSupport.stream(pizzaIngredients).forEach(pizzaIngredient -> pizzaIngredient.pizzaID_FK = pizzaId);
//        insertAll(pizzaIngredients);
    }

    @Transaction
    public void insertPizzaIngredientsTransact(List<PizzaIngredient> pizzaIngredients) {
//        insertPizza(pizza);
//        StreamSupport.stream(pizzaIngredients).forEach(pizzaIngredient -> pizzaIngredient.pizzaID_FK = pizzaId);
        insertAll(pizzaIngredients);
    }
}