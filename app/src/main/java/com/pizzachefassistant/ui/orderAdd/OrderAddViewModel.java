package com.pizzachefassistant.ui.orderAdd;

import android.app.Application;
import android.app.Dialog;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.pizzachefassistant.App;
import com.pizzachefassistant.BR;
import com.pizzachefassistant.databinding.AddIngredientToPizzaBinding;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Order;
import com.pizzachefassistant.repository.model.OrderPizza;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;
import com.pizzachefassistant.databinding.AddPizzaToOrderBinding;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java8.util.stream.Collectors;

import java8.util.stream.StreamSupport;

public class OrderAddViewModel extends AndroidViewModel {

    public OrderObservable orderObservable;

    private MainRepository mainRepository;

    public LiveData<List<Pizza>> pizzas;
    public String[] pizzasArray;

    public PizzaObservable currentlyAddedPizzaObservable;
    public MutableLiveData<List<Pizza>> pizzasSelected;
    public MutableLiveData<List<Integer>> pizzasSelectedAmounts;
    public List<PizzaObservable> orderPizzaObservables;

    private Dialog addPizzaDialog;

    public OrderAddViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        orderObservable = new OrderObservable();
        currentlyAddedPizzaObservable = new PizzaObservable();

        mapLiveDataFromRepo(application);
    }

    private void mapLiveDataFromRepo(Application application) {
        Context appContext = ((App)application).getComponent().getContext();
        pizzasSelected = new MutableLiveData<>();
        pizzasSelected.setValue(new ArrayList<>());
        pizzasSelectedAmounts = new MutableLiveData<>();
        pizzasSelectedAmounts.setValue(new ArrayList<>());
        orderPizzaObservables = new ArrayList<>();

        pizzas = mainRepository.getPizzaList();
//        pizzasArray = new ArrayList<>();

        pizzas.observeForever(pizzas -> {
//            pizzasArray.clear();
            if (pizzas != null) {
                String[] pizzasStrings = StreamSupport.stream(pizzas).map(pizza -> pizza.pizzaName).toArray(String[]::new);//.collect(Collectors.toList());

                pizzasArray = pizzasStrings;

                currentlyAddedPizzaObservable.setTypes(pizzasArray);
            }
        });

    }

    public void onClickSave(View view) {
        Log.i(OrderAddViewModel.class.getSimpleName(), "onClickSave");

        List<Pizza> pizzaList = pizzas.getValue();
        if (pizzaList != null) {
            /**
             * TODO: ...
             */
//            Pizza pizza = pizzas.getValue().get(orderObservable.selectedTypePosition);

            String timeToFinish = orderObservable.timeToFinish;
            String location = orderObservable.location;

            Log.i(OrderAddViewModel.class.getSimpleName(), "onClickSave");

            /**
             * TODO: ...
             */
//            addPizza(pizzaName, cookingInstruction, price, ingredient, 5);
//            addPizzaWithIngredients(pizzaName, cookingInstruction, price, ingredient, 5);

            List<OrderPizza> orderPizzas = StreamSupport.stream(orderPizzaObservables).map(orderPizzaObservables -> {
                return new OrderPizza(
                        pizzaList.get(orderPizzaObservables.selectedTypePosition).id,
                        Integer.parseInt(orderPizzaObservables.neededAmount)
                );
            }).collect(Collectors.toList());

            mainRepository.addOrderWithPizzas(false, timeToFinish, location, orderPizzas);


            Context context = view.getContext();
            closeActivity(context);
        }
    }

    public void onClickCancel(View view) {
        Log.i(OrderAddViewModel.class.getSimpleName(), "onClickCancel");

        Context context = view.getContext();
        closeActivity(context);
    }

    private void closeActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("addOrderClose", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void onAddPizzaButtonClick(View view) {
        addPizzaDialog = new Dialog(view.getContext());
        AddPizzaToOrderBinding binding = AddPizzaToOrderBinding.inflate(LayoutInflater.from(view.getContext()));
        binding.setViewModel(this);
        currentlyAddedPizzaObservable = new PizzaObservable();
        currentlyAddedPizzaObservable.setTypes(pizzasArray);
        binding.setObservable(currentlyAddedPizzaObservable);
        addPizzaDialog.setContentView(binding.getRoot());
        addPizzaDialog.show();
    }

    public void onAddPizzaSaveButtonClick(View view) {
        List<Pizza> pizzasValue = pizzas.getValue();
        if (pizzasValue != null) {
            orderPizzaObservables.add(currentlyAddedPizzaObservable);

            Pizza newPizza = pizzasValue.get(currentlyAddedPizzaObservable.selectedTypePosition);

            List<Pizza> pizzasSelectedValue = pizzasSelected.getValue();
            if (pizzasSelectedValue == null) {
                pizzasSelectedValue = new ArrayList<>();
            }
            List<Integer> pizzasSelectedAmountsValue = pizzasSelectedAmounts.getValue();
            if (pizzasSelectedAmountsValue == null) {
                pizzasSelectedAmountsValue = new ArrayList<>();
            }

            pizzasSelectedValue.add(newPizza);
            pizzasSelectedAmountsValue.add(Integer.parseInt(currentlyAddedPizzaObservable.neededAmount));

            pizzasSelected.setValue(pizzasSelectedValue);
            pizzasSelectedAmounts.setValue(pizzasSelectedAmountsValue);
        }

        addPizzaDialog.dismiss();
    }

    public class OrderObservable extends BaseObservable {

        public String timeToFinish = "";
        public String location = "";

        @Bindable
        public String getTimeToFinish() {
            return timeToFinish;
        }

        public void setTimeToFinish(String timeToFinish) {
            this.timeToFinish = timeToFinish;
            notifyPropertyChanged(BR.timeToFinish);
        }

        @Bindable
        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
            notifyPropertyChanged(BR.location);
        }


    }

    public class PizzaObservable extends BaseObservable {

        public String neededAmount = "1";

        public String[] types = null;

        public String type = null;

        @Bindable
        public String getNeededAmount() {
            return neededAmount;
        }

        public void setNeededAmount(String neededAmount) {
            this.neededAmount = neededAmount;
            notifyPropertyChanged(BR.neededAmount);
        }

        @Bindable
        public String getType() {
            return type;
        }

        @Bindable
        public String[] getTypes() {
            return types;
        }

        public void setTypes(String[] types) {
            this.types = types;
            notifyPropertyChanged(BR.types);
        }

        public void setType(String type) {
            this.type = type;
            notifyPropertyChanged(BR.type);
        }

        public Integer selectedTypePosition = 0;

        @Bindable
        public Integer getSelectedTypePosition() {
            return selectedTypePosition;
        }

        public void setSelectedTypePosition(Integer selectedTypePosition) {
            this.selectedTypePosition = selectedTypePosition;
            type = types[selectedTypePosition];

        }

        @BindingAdapter(value = {"selectedItemAttrChanged"}, requireAll = false)
        public void setSelectedItemPositionListener(AppCompatSpinner view, final InverseBindingListener selectedItemPositionChange) {
            if (selectedItemPositionChange == null) {
                view.setOnItemSelectedListener(null);
            } else {
                view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedItemPositionChange.onChange();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }

        @InverseBindingAdapter(attribute = "selectedItemPosition")
        public Integer getSelectedItemPosition(AppCompatSpinner spinner) {
            return spinner.getSelectedItemPosition();
        }
    }
}
