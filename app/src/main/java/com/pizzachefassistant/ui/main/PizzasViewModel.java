package com.pizzachefassistant.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.view.View;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Pizza;

import java.util.List;

public class PizzasViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<String> exampleText;
    public LiveData<List<Pizza>> pizzaList;

    public PizzasViewModel(Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

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
