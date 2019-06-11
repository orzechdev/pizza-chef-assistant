package com.pizzachefassistant.ui.pizza;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;

import java.util.List;
import java.util.Map;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class PizzaViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<Pizza> pizza;

    public Map<String, Integer> pizzasImages;
    public MutableLiveData<Integer> pizzaImage;

    private int requiredPizzaId = -1;

    public LiveData<List<Ingredient>> ingredients;
    public Map<String, Integer> ingredientsIcons;
    public String[] ingredientsArray;
    public LiveData<List<PizzaIngredient>> pizzaIngredients;
    public LiveData<List<Integer>> ingredientsAmounts;

    public PizzaViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        mapLiveDataFromRepo(application);
    }

    private void mapLiveDataFromRepo(Application application) {
        Context appContext = ((App)application).getComponent().getContext();
        pizzasImages = mainRepository.getPizzaImages(appContext);

        pizzaImage = new MutableLiveData<>();
        ingredientsAmounts = new MutableLiveData<>();

        if (requiredPizzaId != -1) {
            pizza = mainRepository.getPizza(requiredPizzaId);

            pizza.observeForever(pizzaObj -> {
                if (pizzaObj != null) {
                    pizzaImage.setValue(pizzasImages.get(pizzaObj.pizzaImageSrc));
                }
            });

            ingredients = mainRepository.getIngredientListByPizzaId(requiredPizzaId);

            pizzaIngredients = mainRepository.getPizzaIngredientListByPizzaId(requiredPizzaId);

            ingredientsAmounts = Transformations.map(pizzaIngredients, pizzaIngredientsVals -> {
                return StreamSupport.stream(pizzaIngredientsVals).map(pizzaIngredient -> pizzaIngredient.neededAmount).collect(Collectors.toList());
            });
        }

        ingredients = mainRepository.getIngredientList();
//        ingredientsArray = new ArrayList<>();

        ingredients.observeForever(ingredients -> {
//            ingredientsArray.clear();
            if (ingredients != null) {
                String[] ingredientsStrings = StreamSupport.stream(ingredients).map(ingredient -> ingredient.ingredientName).toArray(String[]::new);//.collect(Collectors.toList());
//                ingredientsArray.addAll(ingredientsStrings);
                ingredientsArray = ingredientsStrings;
            }
        });

        ingredientsIcons = mainRepository.getIngredientsIcons(appContext);
    }

    public void setRequiredPizzaId(int id) {
        requiredPizzaId = id;
        pizza = mainRepository.getPizza(requiredPizzaId);

        pizza.observeForever(pizzaObj -> {
            if (pizzaObj != null) {
                pizzaImage.setValue(pizzasImages.get(pizzaObj.pizzaImageSrc));
            }
        });

        ingredients = mainRepository.getIngredientListByPizzaId(requiredPizzaId);

        pizzaIngredients = mainRepository.getPizzaIngredientListByPizzaId(requiredPizzaId);

        ingredientsAmounts = Transformations.map(pizzaIngredients, pizzaIngredientsVals -> {
            return StreamSupport.stream(pizzaIngredientsVals).map(pizzaIngredient -> pizzaIngredient.neededAmount).collect(Collectors.toList());
        });
    }
}
