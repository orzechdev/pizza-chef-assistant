package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModelProviders;
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
import com.pizzachefassistant.databinding.IngredientsFragmentBinding;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {

    private IngredientsFragmentBinding binding;
    private IngredientsViewModel viewModel;
    private ArrayList<String> ingredientImages = new ArrayList<String>();
    private ArrayList<String> ingredientStocks = new ArrayList<String>();
    private ArrayList<String> ingredientCapacities = new ArrayList<String>();
    private ArrayList<String> ingredientNames = new ArrayList<String>();

    public static IngredientsFragment newInstance() {
        return new IngredientsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(IngredientsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.ingredients_fragment, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById
        IngredientsRecyclerViewAdapter adapter = new IngredientsRecyclerViewAdapter(ingredientNames, ingredientStocks, ingredientCapacities, ingredientImages, this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }
}
