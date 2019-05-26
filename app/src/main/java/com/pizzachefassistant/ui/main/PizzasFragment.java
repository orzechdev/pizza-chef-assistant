package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.pizzachefassistant.R;
import com.pizzachefassistant.constants.IntentsConstants;
import com.pizzachefassistant.databinding.PizzasFragmentBinding;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.ui.PizzaActivity;
import com.pizzachefassistant.ui.utils.ItemClickSupport;

import java.util.List;
import java.util.Map;


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

    @BindingAdapter({"setupRecyclerView", "setupRecyclerViewImages"})
    public static void setupRecyclerView(final RecyclerView view, List<Pizza> data, Map<String, Integer> dataImages) {
        final List<Pizza> list = data;

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, 1);
        view.setLayoutManager(layoutManager);
        PizzasRecyclerViewAdapter adapter = new PizzasRecyclerViewAdapter(data, dataImages);
        view.setAdapter(adapter);

        // Based on https://www.littlerobots.nl/blog/Handle-Android-RecyclerView-Clicks/
        ItemClickSupport.addTo(view).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Context context = view.getContext();
                Intent intent = new Intent(context, PizzaActivity.class);

                intent.putExtra(IntentsConstants.PIZZA_ID, list.get(position).id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);
            }
        });
    }
}
