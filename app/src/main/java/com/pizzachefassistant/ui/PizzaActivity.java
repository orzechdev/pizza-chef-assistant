package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.pizzachefassistant.R;
import com.pizzachefassistant.constants.IntentsConstants;
import com.pizzachefassistant.databinding.PizzaItemBinding;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.ui.pizza.PizzaIngredientsAdapter;
import com.pizzachefassistant.ui.pizza.PizzaViewModel;

import java.util.List;
import java.util.Map;

public class PizzaActivity extends AppCompatActivity {

    private PizzaItemBinding binding;
    private PizzaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PizzaViewModel.class);

        int requiredPizzaId = getIntent().getIntExtra(IntentsConstants.PIZZA_ID, 0);
        Log.i("PizzaActivity", "" + requiredPizzaId);
        viewModel.setRequiredPizzaId(requiredPizzaId);

        binding = DataBindingUtil.setContentView(this,R.layout.pizza_item);
        binding.setViewModel(viewModel);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        binding.setLifecycleOwner(this);
    }

    @BindingAdapter({"setupListView", "setupListViewImages"})
    public static void setupRecyclerView(final ListView view, List<Ingredient> data, Map<String, Integer> dataImages) {
        if (data != null && dataImages != null) {
            PizzaIngredientsAdapter adapter = new PizzaIngredientsAdapter(view.getContext(), data, dataImages);
            view.setAdapter(adapter);
        }
    }
}
