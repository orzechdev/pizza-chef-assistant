package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.PizzasFragmentBinding;
import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

public class PizzasFragment extends Fragment {

    private PizzasFragmentBinding binding;
    private PizzasViewModel viewModel;

    public static PizzasFragment newInstance() {
        return new PizzasFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PizzasViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.pizzas_fragment, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @BindingAdapter("setupRecyclerView")
    public static void setupRecyclerView(final RecyclerView view, List<Pizza> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        PizzasRecyclerViewAdapter adapter = new PizzasRecyclerViewAdapter(data);
        view.setAdapter(adapter);
    }
}
