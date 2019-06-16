package com.pizzachefassistant.ui.orderAdd;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.PizzaInOrderItemBinding;
import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;
import java.util.Map;

public class OrderPizzasAdapter extends BaseAdapter {

    public List<Pizza> pizzas;
    public Map<String, Integer> pizzasIcons;
    public List<Integer> pizzasAmounts;
    private Context context;

    public OrderPizzasAdapter(Context context, List<Pizza> pizzas, List<Integer> pizzasAmounts){
        this.context = context;
        this.pizzas = pizzas;
        this.pizzasAmounts = pizzasAmounts;
    }

    @Override
    public int getCount() {
        return pizzas.size();
    }

    @Override
    public Object getItem(int position) {
        return pizzas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PizzaInOrderItemBinding binding;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.pizza_in_order_item, null);
            binding = DataBindingUtil.bind(convertView);
            convertView.setTag(binding);
        }else{
            binding = (PizzaInOrderItemBinding) convertView.getTag();
        }
        Pizza pizza = pizzas.get(position);
        Integer pizzaAmount = pizzasAmounts.get(position);
        binding.setPizza(pizza);
        binding.setPizzaAmount(pizzaAmount);
        return binding.getRoot();
    }
}
