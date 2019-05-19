package com.pizzachefassistant.ui.ingredientEdit;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;

public class IngredientEditViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public IngredientEditViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();
    }

    private void addIngredient(String name) {
        mainRepository.addIngredient(name);
    }

    public void onClickSave(View view) {
        Log.i(IngredientEditViewModel.class.getSimpleName(), "onClickSave");
    }

    public void onClickCancel(View view) {
        Log.i(IngredientEditViewModel.class.getSimpleName(), "onClickCancel");
    }
}
