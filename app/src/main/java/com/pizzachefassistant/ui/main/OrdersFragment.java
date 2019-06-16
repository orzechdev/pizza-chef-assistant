package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.OrdersFragmentBinding;
import com.pizzachefassistant.repository.model.Order;

import java.util.List;

public class OrdersFragment extends Fragment {

    private OrdersFragmentBinding binding;
    private OrdersViewModel viewModel;

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(OrdersViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.orders_fragment, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @BindingAdapter({"setupRecyclerView", "passViewModelToRecyclerView"})
    public static void setupRecyclerView(final RecyclerView view, List<Order> data, OrdersViewModel viewModel) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        view.setLayoutManager(layoutManager);
        OrdersRecyclerViewAdapter adapter = new OrdersRecyclerViewAdapter(data, viewModel);
        view.setAdapter(adapter);
    }
}
