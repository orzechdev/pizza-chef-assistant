package com.pizzachefassistant.ui.main;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pizzachefassistant.BR;
import com.pizzachefassistant.R;
import com.pizzachefassistant.repository.model.Ingredient;

import java.util.List;


public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<IngredientsRecyclerViewAdapter.Viewholder>{
    private static final String TAG = "IngredientsRecyclerView";

    public List<Ingredient> ingredients;

    public IngredientsRecyclerViewAdapter(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.ingredient_item, viewGroup, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Ingredient ingredient = ingredients.get(i);
        viewholder.bind(ingredient);
    }

    @Override
    public int getItemCount() {
        if(ingredients == null) return 0;
        return ingredients.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private final ViewDataBinding binding;

        public Viewholder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Object obj) {
            binding.setVariable(BR.obj, obj);
            binding.executePendingBindings();
        }
    }
}
