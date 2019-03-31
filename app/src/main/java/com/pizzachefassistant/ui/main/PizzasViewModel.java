package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pizzachefassistant.repository.MainRepository;

public class PizzasViewModel extends ViewModel {
    private MainRepository mainRepository;

    public PizzasViewModel() {
        super();
        mainRepository = MainRepository.getInstance();
    }

    public LiveData<String> getPizzasExampleText() {
        return mainRepository.getPizzasExampleText();
    }
}
