package com.pizzachefassistant.repository.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "order_pizza",
        primaryKeys = {"orderID_FK","pizzaID_FK"},
        foreignKeys = {@ForeignKey(entity = Order.class,parentColumns = "orderID", childColumns = "orderID_FK"),
                @ForeignKey(entity = Pizza.class, parentColumns = "pizzaID", childColumns = "pizzaID_FK")
        })

public class OrderPizza {
    public final int orderID_FK;
    public final int pizzaID_FK;
    public final int orderAmount;

    public OrderPizza (final int orderID_FK,final int pizzaID_FK, int orderAmount){
        this.orderID_FK = orderID_FK;
        this.pizzaID_FK = pizzaID_FK;
        this.orderAmount = orderAmount;
    }

}
