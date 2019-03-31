package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pizzachefassistant.repository.MainRepository;

public class IngredientsViewModel extends ViewModel {
    private MainRepository mainRepository;

    public IngredientsViewModel() {
        super();
        mainRepository = MainRepository.getInstance();
    }

    public LiveData<String> getIngredientsExampleText() {
        return mainRepository.getIngredientsExampleText();
    }
}
