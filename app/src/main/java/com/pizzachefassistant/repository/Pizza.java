package com.pizzachefassistant.repository;

import android.arch.persistence.room.PrimaryKey;

public class Pizza {
    @PrimaryKey(autoGenerate = true)
    public final int pizzaID;
    public final String pizzaName;
    public final String cookingInstruction;

    public Pizza (int pizzaID, String pizzaName, String cookingInstruction){
        this.pizzaID = pizzaID;
        this.pizzaName = pizzaName;
        this.cookingInstruction = cookingInstruction;
    }
}
