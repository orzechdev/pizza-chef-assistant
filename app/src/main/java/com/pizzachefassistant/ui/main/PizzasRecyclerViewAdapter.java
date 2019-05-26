package com.pizzachefassistant.ui.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.pizzachefassistant.BR;
import com.pizzachefassistant.R;
import com.pizzachefassistant.repository.model.Pizza;
import java.util.List;
import java.util.Map;

public class PizzasRecyclerViewAdapter extends RecyclerView.Adapter<PizzasRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "PizzasRecyclerViewAd";
    public List<Pizza> pizzas;
    public Map<String, Integer> pizzasIcons;

    public PizzasRecyclerViewAdapter(List<Pizza> pizzas, Map<String, Integer> pizzasIcons) {
        this.pizzas = pizzas;
        this.pizzasIcons = pizzasIcons;
    }

    @NonNull
    @Override
    public PizzasRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.pizza_card, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzasRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Pizza pizza = pizzas.get(i);
        Integer pizzaIcon = pizzasIcons.get(pizza.pizzaImageSrc);
        viewHolder.bind(pizza, pizzaIcon);
    }

    @Override
    public int getItemCount() {
        if(this.pizzas == null)
            return 0;
        return this.pizzas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj, Integer pizzaIcon) {
            binding.setVariable(BR.obj, obj);
            binding.setVariable(BR.objIcon, pizzaIcon);
            binding.executePendingBindings();
        }
    }
}
