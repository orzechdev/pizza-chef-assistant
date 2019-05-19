package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.PizzaItemBinding;
import com.pizzachefassistant.ui.pizza.PizzaViewModel;

public class PizzaActivity extends AppCompatActivity {

    private PizzaItemBinding binding;
    private PizzaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PizzaViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.pizza_item);
        binding.setViewModel(viewModel);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
