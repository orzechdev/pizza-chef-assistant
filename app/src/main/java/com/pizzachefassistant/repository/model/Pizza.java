package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Pizza {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String pizzaName;
    public String cookingInstruction;
    public String pizzaImageSrc;

    @Ignore
    public Pizza(String pizzaName, String cookingInstruction, String pizzaImageSrc){
        this.pizzaName = pizzaName;
        this.cookingInstruction = cookingInstruction;
        this.pizzaImageSrc = pizzaImageSrc;
    }
    public Pizza(int id, String pizzaName, String cookingInstruction, String pizzaImageSrc){
        this.id = id;
        this.pizzaName = pizzaName;
        this.cookingInstruction = cookingInstruction;
        this.pizzaImageSrc = pizzaImageSrc;
    }
}
