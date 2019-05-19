package com.pizzachefassistant.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.pizzachefassistant.R;
import com.pizzachefassistant.repository.database.MainDatabase;
import com.pizzachefassistant.repository.model.Customer;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MainRepository {
    MainDatabase mainDatabase;

    private LiveData<String> ordersExampleText;
    private LiveData<String> pizzasExampleText;
    private LiveData<String> ingredientsExampleText;

    @Inject
    public MainRepository(MainDatabase mainDatabase) {
        this.mainDatabase = mainDatabase;
        Log.i("repo", "constructor");
    }

    public LiveData<String> getOrdersExampleText() {
        ordersExampleText = new MutableLiveData<>();
        ((MutableLiveData<String>) ordersExampleText).postValue("Orders example text DI");
        return ordersExampleText;
    }

    public LiveData<String> getPizzasExampleText() {
        pizzasExampleText = new MutableLiveData<>();
        ((MutableLiveData<String>) pizzasExampleText).postValue("Pizzas example text DI");
        return pizzasExampleText;
    }

    public LiveData<String> getIngredientsExampleText() {
        ingredientsExampleText = new MutableLiveData<>();
        ((MutableLiveData<String>) ingredientsExampleText).postValue("Ingredients example text DI");
        return ingredientsExampleText;
    }

    public LiveData<List<Pizza>> getPizzaList() {
        return mainDatabase.pizzaDao().loadAll();
    }

    public LiveData<List<Order>> getOrderList() {
        return mainDatabase.orderDao().loadAll();
    }

    public LiveData<List<Customer>> getCustomerList() {
        return mainDatabase.customerDao().loadAll();
    }

    public LiveData<List<Ingredient>> getIngredientList() {
        return mainDatabase.ingredientDao().loadAll();
    }

    public void addPizza(final String name, final String cookingInstruction) {
        Log.i("repo", "addPizza");
        List<Pizza> pizzaList = mainDatabase.pizzaDao().loadAll().getValue();
        if (pizzaList == null || pizzaList.size() == 0) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    mainDatabase.pizzaDao().insert(new Pizza(name, cookingInstruction));
                }
            });
            t.start();
        }
    }

    public LiveData<Ingredient> getIngredient(final int id) {
        return mainDatabase.ingredientDao().load(id);
    }

    public void saveIngredient(final Ingredient ingredient) {
        Log.i("repo", "saveIngredient");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mainDatabase.ingredientDao().insert(ingredient);
            }
        });
        t.start();
    }

    public void addIngredient(final String name, final String picRef, final int amount, final int capacity) {
        Log.i("repo", "addIngredient");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mainDatabase.ingredientDao().insert(new Ingredient(name, picRef, amount, capacity));
            }
        });
        t.start();
    }

    public Map<String, Drawable> getIngredientsIcons() {
        Resources resources = Resources.getSystem();

        Map<String, Drawable> iconsList = new HashMap<>();
        iconsList.put("Cheese", resources.getDrawable(R.mipmap.ic_cheese));
        iconsList.put("Ham", resources.getDrawable(R.mipmap.ic_ham));
        iconsList.put("Egg", resources.getDrawable(R.mipmap.ic_egg));
        iconsList.put("Flour", resources.getDrawable(R.mipmap.ic_flour));
        iconsList.put("Onion", resources.getDrawable(R.mipmap.ic_onion));
        iconsList.put("Pepper", resources.getDrawable(R.mipmap.ic_pepper));
        iconsList.put("Poultry", resources.getDrawable(R.mipmap.ic_poultry));
        iconsList.put("Spices", resources.getDrawable(R.mipmap.ic_spices));
        iconsList.put("Tomato", resources.getDrawable(R.mipmap.ic_tomato));
        iconsList.put("Yeast", resources.getDrawable(R.mipmap.ic_yeast));
        iconsList.put("Add", resources.getDrawable(R.mipmap.ic_add));
        iconsList.put("Launcher", resources.getDrawable(R.mipmap.ic_launcher));

        return iconsList;
    }

    public void deleteAndCreateData(
            final List<Order> orders, final List<Customer> customers,
            final List<Pizza> pizzas, final List<Ingredient> ingredients,
            final List<PizzaIngredient> pizzaIngredients, final List<OrderPizza> orderPizzas
    ) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mainDatabase.orderDao().deleteAndCreate(orders);
                mainDatabase.customerDao().deleteAndCreate(customers);
                mainDatabase.pizzaDao().deleteAndCreate(pizzas);
                mainDatabase.ingredientDao().deleteAndCreate(ingredients);
                mainDatabase.pizzaIngredientDao().deleteAndCreate(pizzaIngredients);
                mainDatabase.orderPizzaDao().deleteAndCreate(orderPizzas);
            }
        });
        t.start();
    }
}
