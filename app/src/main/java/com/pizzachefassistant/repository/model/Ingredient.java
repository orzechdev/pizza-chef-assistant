package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public final int id;
    public final String IngredientName;

    public Ingredient(int id, String ingredientName){
        this.id = id;
        this.IngredientName = ingredientName;

    }
}
