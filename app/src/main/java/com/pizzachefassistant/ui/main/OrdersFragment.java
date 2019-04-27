package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.OrdersFragmentBinding;
//import com.pizzachefassistant.dependencies.DaggerFragmentComponent;
import com.pizzachefassistant.dependencies.components.DaggerFragmentComponent;
import com.pizzachefassistant.dependencies.components.FragmentComponent;
import com.pizzachefassistant.dependencies.modules.ContextModule;
import com.pizzachefassistant.dependencies.modules.DatabaseModule;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;
import com.pizzachefassistant.ui.utils.ViewModelFactory;
import com.pizzachefassistant.dependencies.modules.ViewModelModule;

import javax.inject.Inject;

public class OrdersFragment extends Fragment {
    @Inject
    ViewModelFactory viewModelFactory;

    private OrdersFragmentBinding binding;
    private OrdersViewModel viewModel;

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentComponent component = DaggerFragmentComponent.builder()
                .contextModule(new ContextModule(getActivity()))
                .databaseModule(new DatabaseModule())
                .repositoryModule(new RepositoryModule())
                .viewModelModule(new ViewModelModule())
                .build();
        component.inject(this);

        if (viewModelFactory == null) {
            Log.i("viewModelFactory", "NULL");
        } else {
            Log.i("viewModelFactory", "not null");
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(OrdersViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (viewModelFactory == null) {
            Log.i("viewModelFactory", "NULL");
        } else {
            Log.i("viewModelFactory", "not null");
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.orders_fragment, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
