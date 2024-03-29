package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.IngredientsFragmentBinding;
import com.pizzachefassistant.repository.model.Ingredient;

import java.util.List;
import java.util.Map;

public class IngredientsFragment extends Fragment {

    private IngredientsFragmentBinding binding;
    private IngredientsViewModel viewModel;

    public static IngredientsFragment newInstance() {
        return new IngredientsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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

    @BindingAdapter({"setupRecyclerView", "setupRecyclerViewIcons"})
    public static void setupRecyclerViewWithIcons(final RecyclerView view, List<Ingredient> data, Map<String, Integer> dataIcons) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        view.setLayoutManager(layoutManager);
        IngredientsRecyclerViewAdapter adapter = new IngredientsRecyclerViewAdapter(data, dataIcons);
        view.setAdapter(adapter);
    }
}
