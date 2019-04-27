package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Pizza {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String pizzaName;
    public String cookingInstruction;

    public Pizza(int id, String pizzaName, String cookingInstruction){
        this.id = id;
        this.pizzaName = pizzaName;
        this.cookingInstruction = cookingInstruction;
    }

//    public Pizza(String pizzaName, String cookingInstruction){
//        this.pizzaName = pizzaName;
//        this.cookingInstruction = cookingInstruction;
//    }
}
