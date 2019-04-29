package com.pizzachefassistant.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pizzachefassistant.R;
import com.pizzachefassistant.repository.model.Ingredient;

import java.util.ArrayList;
import java.util.List;


public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<IngredientsRecyclerViewAdapter.Viewholder>{
    private static final String TAG = "IngredientsRecyclerView";

    public List<Ingredient> ingredients;

    private ArrayList<String> ingredientNames = new ArrayList<>();
    private ArrayList<String> ingredientStocks = new ArrayList<>();
    private ArrayList<String> ingredientCapacities = new ArrayList<>();
    private ArrayList<String> ingredientImages = new ArrayList<>();
    private Context ingredientContext;

    public IngredientsRecyclerViewAdapter(ArrayList<String> ingredientNames, ArrayList<String> ingredientStocks, ArrayList<String> ingredientCapacities, ArrayList<String> ingredientImages, Context ingredientContext) {
        this.ingredientNames = ingredientNames;
        this.ingredientStocks = ingredientStocks;
        this.ingredientCapacities = ingredientCapacities;
        this.ingredientImages = ingredientImages;
        this.ingredientContext = ingredientContext;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_item, viewGroup);
        Viewholder ingredientHolder = new Viewholder(view);
        return ingredientHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        viewholder.ingredientCapacity.setText(ingredientCapacities.get(i));
        viewholder.ingredientStock.setText(ingredientStocks.get(i));
        viewholder.ingredientName.setText(ingredientNames.get(i));
        // SET IMAGES NOT DONE
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView ingredientImage;
        TextView ingredientName;
        TextView ingredientStock;
        TextView ingredientCapacity;
        RelativeLayout parentLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = itemView.findViewById(R.id.ingredient_image);
            ingredientName = itemView.findViewById(R.id.ingredient_name);
            ingredientStock = itemView.findViewById(R.id.stock);
            ingredientCapacity = itemView.findViewById(R.id.capacity);
        }
    }
}
