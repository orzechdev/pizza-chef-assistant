package com.pizzachefassistant.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.ui.IngredientEditActivity;
import com.pizzachefassistant.ui.PizzaAddActivity;

import java.util.List;
import java.util.Map;

public class PizzasViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<String> exampleText;
    public LiveData<List<Pizza>> pizzas;

    public Map<String, Integer> pizzasImages;

    public PizzasViewModel(Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        mapLiveDataFromRepo(application);
    }

    private void mapLiveDataFromRepo(Application application) {
        exampleText = mainRepository.getPizzasExampleText();
        pizzas = mainRepository.getPizzaList();

        Context appContext = ((App)application).getComponent().getContext();
        pizzasImages = mainRepository.getPizzaImages(appContext);
    }

    public void onClickFab(View view) {
        Log.i("vm", "onClickFab");

        Context context = view.getContext();
        Intent intent = new Intent(context, PizzaAddActivity.class);
        intent.putExtra("isAdd", true);
        context.startActivity(intent);
    }
}
