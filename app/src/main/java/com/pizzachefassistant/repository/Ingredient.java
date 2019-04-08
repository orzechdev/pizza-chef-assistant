package com.pizzachefassistant.repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public final int ingredientID;
    public final String IngredientName;

    public Ingredient(int ingredientID, String ingredientName){
        this.ingredientID = ingredientID;
        this.IngredientName = ingredientName;

    }
}
