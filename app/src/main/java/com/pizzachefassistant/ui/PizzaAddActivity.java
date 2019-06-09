package com.pizzachefassistant.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.pizzachefassistant.R;
import com.pizzachefassistant.databinding.AddPizzaBinding;
import com.pizzachefassistant.ui.pizzaAdd.PizzaAddViewModel;

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



    public void onButtonShowPopupWindowClick(View view) {

        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.add_ingredient_to_pizza, null);
        int color = 0x80000000;
        Drawable drawable = new ColorDrawable(color);
        popupdim.setForeground(drawable);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                int color = 0x00000000;
                Drawable drawable = new ColorDrawable(color);
                popupdim.setForeground(drawable);
            }
        });
    }
}
