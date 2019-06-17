package com.pizzachefassistant.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.pizzachefassistant.App;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.ui.OrderAddActivity;

import java.util.List;

public class OrdersViewModel extends AndroidViewModel {

    private MainRepository mainRepository;

    public LiveData<String> exampleText;
    public LiveData<List<Order>> orders;
    public LiveData<List<Pizza>> pizzas;
    public LiveData<List<OrderPizza>> orderPizzas;

    public OrdersViewModel(Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        exampleText = mainRepository.getOrdersExampleText();
        orders = mainRepository.getOrderList();
    }

    public void onClickFab(View view) {
        Log.i("vm", "onClickFab");

        Context context = view.getContext();
        Intent intent = new Intent(context, OrderAddActivity.class);
        intent.putExtra("isAdd", true);
        context.startActivity(intent);
    }

    public void onOrderCheckedChanged(Boolean isChecked, int orderId) {
        Log.i("vm", "onOrderCheckedChanged" + orderId);
        mainRepository.updateOrderStatus(orderId, isChecked);
    }

    //public void getPizzaName()

}
