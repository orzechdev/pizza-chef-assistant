package com.pizzachefassistant.ui.main;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.pizzachefassistant.repository.MainRepository;

public class PizzasViewModel extends ViewModel {
    private MainRepository mainRepository;

    public LiveData<String> exampleText;

    public PizzasViewModel() {
        super();
        mainRepository = MainRepository.getInstance();
        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        exampleText = mainRepository.getPizzasExampleText();
    }
}
