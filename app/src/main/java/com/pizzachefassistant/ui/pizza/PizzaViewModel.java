package com.pizzachefassistant.ui.pizza;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

import java8.util.stream.StreamSupport;

public class PizzaViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<Pizza> pizza;

    private int requiredPizzaId = -1;

    public LiveData<List<Ingredient>> ingredients;
    public String[] ingredientsArray;

    public PizzaViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        if (requiredPizzaId != -1) {
            pizza = mainRepository.getPizza(requiredPizzaId);
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
    }

    public void setRequiredPizzaId(int id) {
        requiredPizzaId = id;
        pizza = mainRepository.getPizza(requiredPizzaId);
    }
}
