package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pizzachefassistant.R;

public class PizzasFragment extends Fragment {

    private PizzasViewModel viewModel;

    public static PizzasFragment newInstance() {
        return new PizzasFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pizzas_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PizzasViewModel.class);
        setupObservables();
    }

    private void setupObservables(){
        LifecycleOwner lifecycleOwner = getViewLifecycleOwner();

        viewModel.getPizzasExampleText().observe(lifecycleOwner, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if(getView() != null) {
                    TextView exampleTextView = getView().findViewById(R.id.message);
                    exampleTextView.setText(s);
                }
            }
        });
    }
}
