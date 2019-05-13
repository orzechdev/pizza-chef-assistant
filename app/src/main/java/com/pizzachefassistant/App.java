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


        ingredients.add(new Ingredient(12, "Yeast"));
        ingredients.add(new Ingredient(13, "Egg"));
        ingredients.add(new Ingredient(14, "Ham"));
        ingredients.add(new Ingredient(15, "Tomato"));
        ingredients.add(new Ingredient(16, "Cheese"));
        ingredients.add(new Ingredient(17, "Onion"));
        ingredients.add(new Ingredient(18, "Pepper"));
        ingredients.add(new Ingredient(18, "Poultry"));
        ingredients.add(new Ingredient(19, "Pepper"));
        ingredients.add(new Ingredient(20, "Onion"));
        ingredients.add(new Ingredient(21, "Pepper"));
        ingredients.add(new Ingredient(22, "Poultry"));
        ingredients.add(new Ingredient(23, "Pepper"));
        pizzas.add(new Pizza(20, "Margherita", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(21, "Marinara", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(22, "Quattro Formagi", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(23, "Frutti di Mare", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(23, "Napoli", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(24, "Carbonara", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(25, "Margherita", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(26, "Marinara", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(27, "Quattro Formagi", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(28, "Frutti di Mare", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(29, "Napoli", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(30, "Margherita", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(31, "Marinara", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(32, "Quattro Formagi", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(33, "Frutti di Mare", "DO something, then something, and once again something..."));
        pizzas.add(new Pizza(34, "Napoli", "DO something, then something, and once again something..."));

        /*
         * TODO:...
         */

        mainRepository.deleteAndCreateData(orders, customers, pizzas, ingredients, pizzaIngredients, orderPizzas);
    }
}