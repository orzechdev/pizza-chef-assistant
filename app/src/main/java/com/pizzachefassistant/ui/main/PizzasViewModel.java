package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

public class PizzasViewModel extends ViewModel {
    private MainRepository mainRepository;

    public LiveData<String> exampleText;
    public LiveData<List<Pizza>> pizzaList;

    public PizzasViewModel(MainRepository mainRepository) {
        super();
        this.mainRepository = mainRepository;
        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        exampleText = mainRepository.getPizzasExampleText();
        pizzaList = mainRepository.getPizzaList();
    }

    public void onClickAddItem(View view) {
        Log.i("vm", "onClickAddItem");
        mainRepository.addPizza("Pizza Testowa", "Instrukcja testowa");
    }
}
