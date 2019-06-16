package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddOrderBinding;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.ui.orderAdd.OrderAddViewModel;
import com.pizzachefassistant.ui.orderAdd.OrderPizzasAdapter;

import java.util.List;
import java.util.Map;

public class OrderAddActivity extends AppCompatActivity {

    private android.support.constraint.ConstraintLayout popupdim;
    private AddOrderBinding binding;
    private OrderAddViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(OrderAddViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.add_order);
        binding.setViewModel(viewModel);
        binding.setObservable(viewModel.orderObservable);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @BindingAdapter({"setupListView", "setupListViewAmounts"})
    public static void setupListView(final ListView view, List<Pizza> pizzas, List<Integer> pizzasAmounts) {
        if (pizzas != null && pizzasAmounts != null) {
            OrderPizzasAdapter adapter = new OrderPizzasAdapter(view.getContext(), pizzas, pizzasAmounts);
            view.setAdapter(adapter);
        }
    }
}
