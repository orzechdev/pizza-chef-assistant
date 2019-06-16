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
import java.util.Calendar;
import java.util.List;

public class App extends Application {

    private AppComponent component;

    private static final boolean INIT_SAMPLE_DATA = true;//false;//true;

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

        ingredients.add(new Ingredient(10, "Spices", "Spices", 100, 200));
        ingredients.add(new Ingredient(11, "Flour", "Flour", 100, 200));
        ingredients.add(new Ingredient(12, "Yeast", "Yeast", 100, 200));
        ingredients.add(new Ingredient(13, "Egg", "Egg", 100, 200));
        ingredients.add(new Ingredient(14, "Ham", "Ham", 100, 200));
        ingredients.add(new Ingredient(15, "Tomato", "Tomato", 100, 200));
        ingredients.add(new Ingredient(16, "Cheese", "Cheese", 100, 200));
        ingredients.add(new Ingredient(17, "Onion", "Onion", 100, 200));
        ingredients.add(new Ingredient(18, "Pepper", "Pepper", 100, 200));
        ingredients.add(new Ingredient(18, "Poultry", "Poultry", 100, 200));
        ingredients.add(new Ingredient(19, "Pepper", "Pepper", 100, 200));
        pizzas.add(new Pizza(20, "Margherita", "DO something, then something, and once again something...", "pic_carbonara", "12 PLN"));
        pizzas.add(new Pizza(21, "Marinara", "DO something, then something, and once again something...", "pic_margherita", "14 PLN"));
        pizzas.add(new Pizza(22, "Quattro Formagi buydsbvie", "DO something, then something, and once again something...", "pic_marinara", "14 PLN"));
        pizzas.add(new Pizza(23, "Frutti di Mare", "DO something, then something, and once again something...", "pic_frutti", "16 PLN"));
        pizzas.add(new Pizza(24, "Carbonara", "DO something, then something, and once again something...", "pic_napoli", "20 PLN"));
        pizzas.add(new Pizza(25, "Margherita", "DO something, then something, and once again something...", "pic_quattro", "16 PLN"));
        pizzas.add(new Pizza(26, "Marinara", "DO something, then something, and once again something...", "pic_carbonara", "16 PLN"));
        pizzas.add(new Pizza(27, "Quattro Formagi", "DO something, then something, and once again something...", "pic_frutti", "24 PLN"));
        pizzas.add(new Pizza(28, "Frutti di Mare", "DO something, then something, and once again something...", "pic_margherita", "12 PLN"));
        pizzas.add(new Pizza(29, "Napoli", "DO something, then something, and once again something...", "pic_marinara", "16 PLN"));
        pizzas.add(new Pizza(30, "Margherita", "DO something, then something, and once again something...", "pic_napoli", "24 PLN"));
        pizzas.add(new Pizza(31, "Marinara", "DO something, then something, and once again something...", "pic_quattro", "20 PLN"));
        pizzas.add(new Pizza(32, "Quattro Formagi", "DO something, then something, and once again something...", "pic_carbonara", "20 PLN"));
        pizzas.add(new Pizza(33, "Frutti di Mare", "DO something, then something, and once again something...", "pic_frutti", "18 PLN"));
        pizzas.add(new Pizza(34, "Napoli", "DO something, then something, and once again something...", "pic_margherita", "16 PLN"));
        orders.add(new Order(43, false, "21:23:12"));
        orders.add(new Order(44, false, "11:23:23"));
        orders.add(new Order(45, false, "11:26:23"));
        orders.add(new Order(46, false, "11:23:27"));
        orders.add(new Order(47, true, "11:26:23"));
        orders.add(new Order(48, true, "11:29:23"));
        orders.add(new Order(49, true, "11:33:23"));
        orderPizzas.add(new OrderPizza(43, 20, 1));
        orderPizzas.add(new OrderPizza(44, 21, 1));
        orderPizzas.add(new OrderPizza(45, 22, 1));
        orderPizzas.add(new OrderPizza(46, 23, 1));
        orderPizzas.add(new OrderPizza(47, 24, 1));
        pizzaIngredients.add(new PizzaIngredient(22, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(22, 13, 1));
        pizzaIngredients.add(new PizzaIngredient(22, 14, 1));
        pizzaIngredients.add(new PizzaIngredient(22, 17, 1));
        pizzaIngredients.add(new PizzaIngredient(22, 18, 1));
        pizzaIngredients.add(new PizzaIngredient(20, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(20, 13, 1));
        pizzaIngredients.add(new PizzaIngredient(20, 15, 1));
        pizzaIngredients.add(new PizzaIngredient(20, 16, 1));
        pizzaIngredients.add(new PizzaIngredient(20, 19, 1));
        pizzaIngredients.add(new PizzaIngredient(21, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(21, 14, 1));
        pizzaIngredients.add(new PizzaIngredient(21, 19, 1));
        pizzaIngredients.add(new PizzaIngredient(21, 17, 1));
        pizzaIngredients.add(new PizzaIngredient(21, 18, 1));
        pizzaIngredients.add(new PizzaIngredient(23, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(23, 13, 1));
        pizzaIngredients.add(new PizzaIngredient(23, 14, 1));
        pizzaIngredients.add(new PizzaIngredient(23, 15, 1));
        pizzaIngredients.add(new PizzaIngredient(24, 14, 1));
        pizzaIngredients.add(new PizzaIngredient(24, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(24, 13, 1));
        pizzaIngredients.add(new PizzaIngredient(24, 17, 1));
        pizzaIngredients.add(new PizzaIngredient(25, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(25, 13, 1));
        pizzaIngredients.add(new PizzaIngredient(25, 14, 1));
        pizzaIngredients.add(new PizzaIngredient(26, 12, 1));
        pizzaIngredients.add(new PizzaIngredient(26, 15, 1));
        pizzaIngredients.add(new PizzaIngredient(26, 14, 1));




        /*
         * TODO:...
         */

        mainRepository.deleteAndCreateData(orders, customers, pizzas, ingredients, pizzaIngredients, orderPizzas);
    }
}