package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "pizza_ingredient",
        primaryKeys = {"pizzaID_FK", "ingredientID_FK"},
        foreignKeys = {@ForeignKey(entity = Pizza.class, parentColumns = "pizzaID", childColumns = "pizzaID_FK"),
                        @ForeignKey(entity = Ingredient.class, parentColumns = "ingredientID", childColumns = "ingredientID_FK")
})

public class PizzaIngredient {
    public final int pizzaID_FK;
    public final int ingredientID_FK;
    public final int neededAmount;

    public PizzaIngredient(final int pizzaID_FK, final int ingredientID_FK, int neededAmount){
        this.pizzaID_FK = pizzaID_FK;
        this.ingredientID_FK = ingredientID_FK;
        this.neededAmount = neededAmount;
    }
}
