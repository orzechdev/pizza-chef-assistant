package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddPizzaBinding;
import com.pizzachefassistant.ui.pizzaAdd.PizzaAddViewModel;

public class PizzaAddActivity extends AppCompatActivity {

    private AddPizzaBinding binding;
    private PizzaAddViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PizzaAddViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.add_pizza);
        binding.setViewModel(viewModel);
        binding.setObservable(viewModel.pizzaObservable);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
