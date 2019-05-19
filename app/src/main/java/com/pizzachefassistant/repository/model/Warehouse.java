package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(
        primaryKeys = {"warehouseUserID","warehouseIngredientID"},
        foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "warehouseUserID"),
                       @ForeignKey(entity = Ingredient.class, parentColumns = "id", childColumns = "warehouseIngredientID")
})
public class Warehouse {
    public int warehouseUserID;
    public int warehouseIngredientID;
    public int amount;
    public int capacity;

    public Warehouse (int warehouseIngredientID, int warehouseUserID, int amount, int capacity){
        this.warehouseIngredientID = warehouseIngredientID;
        this.warehouseUserID = warehouseUserID;
        this.amount = amount;
        this.capacity = capacity;
    }

}
