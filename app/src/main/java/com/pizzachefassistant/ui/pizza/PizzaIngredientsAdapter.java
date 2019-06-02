package com.pizzachefassistant.ui.pizza;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.PizzaItemIngredientBinding;
import com.pizzachefassistant.repository.model.Ingredient;

import java.util.List;
import java.util.Map;

public class PizzaIngredientsAdapter extends BaseAdapter {

    public List<Ingredient> ingredients;
    public Map<String, Integer> ingredientsIcons;
    private Context context;

    public PizzaIngredientsAdapter(Context context, List<Ingredient> ingredients, Map<String, Integer> ingredientsIcons){
        this.context = context;
        this.ingredients = ingredients;
        this.ingredientsIcons = ingredientsIcons;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PizzaItemIngredientBinding binding;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.pizza_item_ingredient, null);
            binding = DataBindingUtil.bind(convertView);
            convertView.setTag(binding);
        }else{
            binding = (PizzaItemIngredientBinding) convertView.getTag();
        }
        Ingredient ingredient = ingredients.get(position);
        Integer ingredientIcon = ingredientsIcons.get(ingredient.picRef);
        binding.setIngredient(ingredient);
        binding.setIngredientIcon(ingredientIcon);
        return binding.getRoot();
    }
}
