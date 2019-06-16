package com.pizzachefassistant.ui.orderAdd;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddPizzaToOrderBinding;

public class AddPizzaDialog extends Dialog {
    public AddPizzaDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AddPizzaToOrderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.add_pizza_to_order, null, false);
        setContentView(binding.getRoot());
    }
}