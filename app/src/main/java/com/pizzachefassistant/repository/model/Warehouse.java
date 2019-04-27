package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "warehouse_join",
        primaryKeys = {"warehouseUserID","warehouseIngredientID"},
        foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "userID", childColumns = "warehouseUserID"),
                       @ForeignKey(entity = Ingredient.class, parentColumns = "ingredientID", childColumns = "warehouseIngredientID")
})

public class Warehouse {
    public final int warehouseUserID;
    public final int warehouseIngredientID;
    public final int amount;
    public final int capacity;

    public Warehouse (final int warehouseIngredientID,final int warehouseUserID, int amount, int capacity){
        this.warehouseIngredientID = warehouseIngredientID;
        this.warehouseUserID = warehouseUserID;
        this.amount = amount;
        this.capacity = capacity;
    }

}
