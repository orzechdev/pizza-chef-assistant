package com.pizzachefassistant.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.pizzachefassistant.repository.database.MainDatabase;
import com.pizzachefassistant.repository.model.Customer;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

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
                    mainDatabase.pizzaDao().insert(new Pizza(1277, name, cookingInstruction));
                }
            });
            t.start();
        }
    }
}
