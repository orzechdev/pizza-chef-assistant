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
    public int amount;
    public int capacity;

    @Ignore
    public Ingredient(String ingredientName, String picRef, int amount, int capacity){
        this.ingredientName = ingredientName;
        this.picRef = picRef;
        this.amount = amount;
        this.capacity = capacity;
    }
    public Ingredient(int id, String ingredientName, String picRef, int amount, int capacity){
        this.id = id;
        this.ingredientName = ingredientName;
        this.picRef = picRef;
        this.amount = amount;
        this.capacity = capacity;
    }
}
