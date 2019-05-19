package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddIngredientBinding;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.ui.ingredientEdit.IngredientEditViewModel;

import java.util.List;

public class IngredientEditActivity extends AppCompatActivity {

    private AddIngredientBinding binding;
    private IngredientEditViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(IngredientEditViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.add_ingredient);
        binding.setViewModel(viewModel);
        binding.setObservable(viewModel.ingredientObservable);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @BindingAdapter("entries")
    public static void setEntries(Spinner spinner, String[] spinnerItems) {
        if (spinnerItems != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(spinner.getContext(), android.R.layout.simple_spinner_item, spinnerItems);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }
}