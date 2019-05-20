package com.pizzachefassistant.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.ui.IngredientEditActivity;

import java.util.List;
import java.util.Map;

public class IngredientsViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<List<Ingredient>> ingredients;
    public LiveData<String> exampleText;

    public Map<String, Drawable> ingredientsIcons;

    public IngredientsViewModel(Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        ingredients = mainRepository.getIngredientList();
        exampleText = mainRepository.getIngredientsExampleText();

//        ingredientsIcons = mainRepository.getIngredientsIcons();

        // Test
//        mainRepository.addIngredient("Cheese");
//        mainRepository.addIngredient("Tomato");
    }

    public void onClickFab(View view) {
        Log.i("vm", "onClickFab");

        Context context = view.getContext();
        Intent intent = new Intent(context, IngredientEditActivity.class);
        intent.putExtra("isAdd", true);
        context.startActivity(intent);
    }
}
