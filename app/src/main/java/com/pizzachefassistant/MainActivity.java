package com.pizzachefassistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pizzachefassistant.ui.main.IngredientsFragment;
import com.pizzachefassistant.ui.main.OrdersFragment;
import com.pizzachefassistant.ui.main.PizzasFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            switch (item.getItemId()) {
                case R.id.navigation_orders:
                    Fragment ordersFragment = fragmentManager.findFragmentByTag("orders");
                    ordersFragment = ordersFragment != null ? ordersFragment : OrdersFragment.newInstance();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ordersFragment, "orders")
                            .commitNow();
                    return true;
                case R.id.navigation_pizzas:
                    Fragment pizzasFragment = fragmentManager.findFragmentByTag("pizzas");
                    pizzasFragment = pizzasFragment != null ? pizzasFragment : PizzasFragment.newInstance();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, pizzasFragment, "pizzas")
                            .commitNow();
                    return true;
                case R.id.navigation_ingredients:
                    Fragment ingredientsFragment = fragmentManager.findFragmentByTag("ingredients");
                    ingredientsFragment = ingredientsFragment != null ? ingredientsFragment : IngredientsFragment.newInstance();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, ingredientsFragment, "ingredients")
                            .commitNow();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, OrdersFragment.newInstance())
                    .commitNow();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
