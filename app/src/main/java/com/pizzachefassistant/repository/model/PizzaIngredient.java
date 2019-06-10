package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

@Entity(
        primaryKeys = {"pizzaID_FK", "ingredientID_FK"},
        foreignKeys = {@ForeignKey(entity = Pizza.class, parentColumns = "id", childColumns = "pizzaID_FK"),
                        @ForeignKey(entity = Ingredient.class, parentColumns = "id", childColumns = "ingredientID_FK")},
        indices = {@Index("pizzaID_FK"), @Index("ingredientID_FK")}
)
public class PizzaIngredient {
    public int pizzaID_FK;
    public int ingredientID_FK;
    public int neededAmount;

    public PizzaIngredient(int pizzaID_FK, int ingredientID_FK, int neededAmount){
        this.pizzaID_FK = pizzaID_FK;
        this.ingredientID_FK = ingredientID_FK;
        this.neededAmount = neededAmount;
    }

    @Ignore
    public PizzaIngredient(int ingredientID_FK, int neededAmount){
        this.ingredientID_FK = ingredientID_FK;
        this.neededAmount = neededAmount;
    }
}
