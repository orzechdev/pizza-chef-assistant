package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(
        primaryKeys = {"warehouseIngredientID"},
        foreignKeys = {@ForeignKey(entity = Ingredient.class, parentColumns = "id", childColumns = "warehouseIngredientID")
})
public class Warehouse {
    public int warehouseIngredientID;
    public int amount;
    public int capacity;

    public Warehouse (int warehouseIngredientID, int amount, int capacity){
        this.warehouseIngredientID = warehouseIngredientID;
        this.amount = amount;
        this.capacity = capacity;
    }
}
