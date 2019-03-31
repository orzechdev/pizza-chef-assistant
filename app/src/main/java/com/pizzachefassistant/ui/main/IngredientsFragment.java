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

public class IngredientsFragment extends Fragment {

    private IngredientsViewModel viewModel;

    public static IngredientsFragment newInstance() {
        return new IngredientsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ingredients_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(IngredientsViewModel.class);
        setupObservables();
    }

    private void setupObservables(){
        LifecycleOwner lifecycleOwner = getViewLifecycleOwner();

        viewModel.getIngredientsExampleText().observe(lifecycleOwner, new Observer<String>() {
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
