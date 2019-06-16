package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;

@Entity(
        primaryKeys = {"orderID_FK","pizzaID_FK"},
        foreignKeys = {@ForeignKey(entity = Order.class, parentColumns = "id", childColumns = "orderID_FK"),
                        @ForeignKey(entity = Pizza.class, parentColumns = "id", childColumns = "pizzaID_FK")},
        indices = {@Index("orderID_FK"), @Index("pizzaID_FK")}
)
public class OrderPizza {
    public int orderID_FK;
    public int pizzaID_FK;
    public int orderAmount;

    public OrderPizza(int orderID_FK, int pizzaID_FK, int orderAmount){
        this.orderID_FK = orderID_FK;
        this.pizzaID_FK = pizzaID_FK;
        this.orderAmount = orderAmount;
    }

    @Ignore
    public OrderPizza(int pizzaID_FK, int orderAmount){
        this.pizzaID_FK = pizzaID_FK;
        this.orderAmount = orderAmount;
    }
}
