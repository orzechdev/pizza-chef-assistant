package com.pizzachefassistant;

import android.app.Application;

import com.pizzachefassistant.dependencies.components.AppComponent;
import com.pizzachefassistant.dependencies.components.DaggerAppComponent;
import com.pizzachefassistant.dependencies.modules.ContextModule;
import com.pizzachefassistant.dependencies.modules.DatabaseModule;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Customer;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private AppComponent component;

    private static final boolean INIT_SAMPLE_DATA = true;

//    @Inject
//    Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .databaseModule(new DatabaseModule())
                .repositoryModule(new RepositoryModule())
                .build();
        getComponent().inject(this); // inject retrofit here

        if (INIT_SAMPLE_DATA) {
            initSampleData();
        }
    }

    public AppComponent getComponent() {
        return component;
    }

    private void initSampleData() {
        MainRepository mainRepository = getComponent().getRepository();

        List<Order> orders = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<Pizza> pizzas = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        List<PizzaIngredient> pizzaIngredients = new ArrayList<>();
        List<OrderPizza> orderPizzas = new ArrayList<>();

        ingredients.add(new Ingredient(14, "Ham"));
        ingredients.add(new Ingredient(15, "Tomato"));
        ingredients.add(new Ingredient(16, "Cheese"));
        pizzas.add(new Pizza(20, "Margerita", "DO something, then something, and once again something..."));
        /*
         * TODO:...
         */

        mainRepository.deleteAndCreateData(orders, customers, pizzas, ingredients, pizzaIngredients, orderPizzas);
    }
}