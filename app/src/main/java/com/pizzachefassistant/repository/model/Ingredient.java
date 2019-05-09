package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String ingredientName;

    @Ignore
    public Ingredient(String ingredientName){
        this.ingredientName = ingredientName;
    }
    public Ingredient(int id, String ingredientName){
        this.id = id;
        this.ingredientName = ingredientName;
    }
}
