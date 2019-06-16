package com.pizzachefassistant.ui.orderAdd;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddIngredientToPizzaBinding;

/*
public class AddPizzaDialog {
    public AddPizzaDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AddIngredientDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()));
//        setContentView(binding.getRoot());

        AddPizzaToOrderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.add_pizza_to_order, null, false);
        setContentView(binding.getRoot());
    }
}
