package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.PizzasFragmentBinding;
import com.pizzachefassistant.dependencies.components.DaggerFragmentComponent;
import com.pizzachefassistant.dependencies.components.FragmentComponent;
import com.pizzachefassistant.dependencies.modules.ContextModule;
import com.pizzachefassistant.ui.utils.ViewModelFactory;
import com.pizzachefassistant.dependencies.modules.ViewModelModule;

import javax.inject.Inject;

public class PizzasFragment extends Fragment {
    @Inject
    ViewModelFactory viewModelFactory;

    private PizzasFragmentBinding binding;
    private PizzasViewModel viewModel;

    public static PizzasFragment newInstance() {
        return new PizzasFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentComponent component = DaggerFragmentComponent.builder()
                .contextModule(new ContextModule(getActivity()))
                .viewModelModule(new ViewModelModule())
                .build();
        component.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PizzasViewModel.class);
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
}
