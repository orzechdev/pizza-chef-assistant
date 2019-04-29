package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String ingredientName;

    public Ingredient(String ingredientName){
        this.ingredientName = ingredientName;
    }
}
