package com.pizzachefassistant.ui.pizzaAdd;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddIngredientToPizzaBinding;

public class AddIngredientDialog extends Dialog {
    public AddIngredientDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AddIngredientDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()));
//        setContentView(binding.getRoot());

        AddIngredientToPizzaBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.add_ingredient_to_pizza, null, false);
        setContentView(binding.getRoot());
    }
}