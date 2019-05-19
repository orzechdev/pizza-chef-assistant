package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddIngredientBinding;
import com.pizzachefassistant.ui.ingredientEdit.IngredientEditViewModel;

public class IngredientEditActivity extends AppCompatActivity {

    private AddIngredientBinding binding;
    private IngredientEditViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(IngredientEditViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.add_ingredient);
        binding.setViewModel(viewModel);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

}