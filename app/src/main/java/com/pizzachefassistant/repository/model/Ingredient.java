package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String ingredientName;
    public String picRef;

    @Ignore
    public Ingredient(String ingredientName, String picRef){
        this.ingredientName = ingredientName;
        this.picRef = picRef;
    }
    public Ingredient(int id, String ingredientName, String picRef){
        this.id = id;
        this.ingredientName = ingredientName;
        this.picRef = picRef;
    }
}
