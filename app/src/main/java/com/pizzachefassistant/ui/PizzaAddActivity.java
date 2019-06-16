package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddPizzaBinding;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.PizzaIngredient;
import com.pizzachefassistant.ui.pizza.PizzaIngredientsAdapter;
import com.pizzachefassistant.ui.pizzaAdd.PizzaAddViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @BindingAdapter({"setupListView", "setupListViewImages", "setupListViewAmounts"})
    public static void setupListView(final ListView view, List<Ingredient> ingredients, Map<String, Integer> ingredientsImages, List<Integer> ingredientsAmounts) {
        if (ingredients != null && ingredientsImages != null && ingredientsAmounts != null) {
            PizzaIngredientsAdapter adapter = new PizzaIngredientsAdapter(view.getContext(), ingredients, ingredientsImages, ingredientsAmounts);
            view.setAdapter(adapter);
        }
    }
}
