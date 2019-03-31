package com.pizzachefassistant.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.pizzachefassistant.repository.MainRepository;

public class OrdersViewModel extends ViewModel {
    private MainRepository mainRepository;

    public OrdersViewModel() {
        super();
        mainRepository = MainRepository.getInstance();
    }

    public LiveData<String> getOrdersExampleText() {
        return mainRepository.getOrdersExampleText();
    }
}
