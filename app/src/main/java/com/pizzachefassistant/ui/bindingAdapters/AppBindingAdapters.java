package com.pizzachefassistant.ui.bindingAdapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.ui.orderAdd.OrderPizzasAdapter;

import java.util.List;

public class AppBindingAdapters {

    @BindingAdapter("imgSrc")
    public static void setImageResource(ImageView imageView, Integer resource) {
        if (resource != null) {
            imageView.setImageResource(resource);
        }
    }

    @BindingAdapter({"setupListPizzaView", "setupListPizzaViewAmounts"})
    public static void setupListView(final ListView view, List<Pizza> pizzas, List<Integer> pizzasAmount) {
        if (pizzas != null && pizzasAmount != null) {
            OrderPizzasAdapter adapter = new OrderPizzasAdapter(view.getContext(), pizzas, pizzasAmount);
            view.setAdapter(adapter);
        }
    }

}