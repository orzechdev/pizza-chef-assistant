package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddPizzaBinding;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.PizzaIngredient;
import com.pizzachefassistant.ui.pizza.PizzaIngredientsAdapter;
import com.pizzachefassistant.ui.pizzaAdd.PizzaAddViewModel;

import java.util.List;
import java.util.Map;

public class PizzaAddActivity extends AppCompatActivity {

    private AddPizzaBinding binding;
    private PizzaAddViewModel viewModel;
    private android.support.constraint.ConstraintLayout popupdim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PizzaAddViewModel.class);

        binding = DataBindingUtil.setContentView(this,R.layout.add_pizza);
        binding.setViewModel(viewModel);
        binding.setObservable(viewModel.pizzaObservable);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        popupdim = findViewById(R.id.add_pizza_popup);
    }


    @BindingAdapter({"setupListView", "setupListViewImages", "setupListViewAmounts"})
    public static void setupListView(final ListView view, List<Ingredient> ingredients, Map<String, Integer> ingredientsImages, List<Integer> ingredientsAmounts) {
        if (ingredients != null && ingredientsImages != null && ingredientsAmounts != null) {
            PizzaIngredientsAdapter adapter = new PizzaIngredientsAdapter(view.getContext(), ingredients, ingredientsImages, ingredientsAmounts);
            view.setAdapter(adapter);
        }
    }

    public void onButtonShowPopupWindowClick(View view) {

//        LayoutInflater inflater = (LayoutInflater)
//                getSystemService(LAYOUT_INFLATER_SERVICE);
//        View popupView = inflater.inflate(R.layout.add_ingredient_to_pizza, null);
//        int color = 0x80000000;
//        Drawable drawable = new ColorDrawable(color);
//        popupdim.setForeground(drawable);
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        boolean focusable = true;
////        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


//        DialogOlaBookingConfirmedBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout. dialog_ola_booking_confirmed, null, false);
//        setContentView(binding.getRoot());
//        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
//        popupView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                popupWindow.dismiss();
//                return true;
//            }
//        });
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                int color = 0x00000000;
//                Drawable drawable = new ColorDrawable(color);
//                popupdim.setForeground(drawable);
//            }
//        });
    }
}
