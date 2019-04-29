package com.pizzachefassistant.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;

import java.util.List;

public class IngredientsViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<List<Ingredient>> ingredients;
    public LiveData<String> exampleText;

    public IngredientsViewModel(Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        ingredients = mainRepository.getIngredientList();
        exampleText = mainRepository.getIngredientsExampleText();
    }
}
