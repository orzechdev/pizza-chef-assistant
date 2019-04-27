package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pizzachefassistant.repository.MainRepository;

public class IngredientsViewModel extends ViewModel {
    private MainRepository mainRepository;

    public LiveData<String> exampleText;

    public IngredientsViewModel(MainRepository mainRepository) {
        super();
        this.mainRepository = mainRepository;
        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        exampleText = mainRepository.getIngredientsExampleText();
    }
}
