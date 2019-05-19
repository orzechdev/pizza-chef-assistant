package com.pizzachefassistant.ui.pizzaAdd;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.pizzachefassistant.App;
import com.pizzachefassistant.BR;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.ui.MainActivity;

import java.util.List;

import java8.util.stream.StreamSupport;

public class PizzaAddViewModel extends AndroidViewModel {

    public PizzaObservable pizzaObservable;

    private MainRepository mainRepository;

    public LiveData<List<Ingredient>> ingredients;
    public String[] ingredientsArray;

    public PizzaAddViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        pizzaObservable = new PizzaObservable();

        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        ingredients = mainRepository.getIngredientList();
//        ingredientsArray = new ArrayList<>();

        ingredients.observeForever(ingredients -> {
//            ingredientsArray.clear();
            if (ingredients != null) {
                String[] ingredientsStrings = StreamSupport.stream(ingredients).map(ingredient -> ingredient.ingredientName).toArray(String[]::new);//.collect(Collectors.toList());
//                ingredientsArray.addAll(ingredientsStrings);
                ingredientsArray = ingredientsStrings;

                pizzaObservable.setTypes(ingredientsArray);
            }
        });
    }

    private void addPizza(Ingredient ingredient, int addedAmount) {
        ingredient.amount = ingredient.amount + addedAmount;

//        mainRepository.saveIngredient(ingredient);
    }

    public void onClickSave(View view) {
        Log.i(PizzaAddViewModel.class.getSimpleName(), "onClickSave");

//        List<Ingredient> ingredientList = ingredients.getValue();
//        if (ingredientList != null) {
//            Ingredient pizza = ingredients.getValue().get(pizzaObservable.selectedTypePosition);
//
//            String pizzaName = pizzaObservable.pizzaName;
//            Log.i(PizzaAddViewModel.class.getSimpleName(), "onClickSave" + pizzaObservable.pizzaName);
//
//            addPizza(pizza, pizzaName);
//
//            Context context = view.getContext();
//            closeActivity(context);
//        }
    }

    public void onClickCancel(View view) {
        Log.i(PizzaAddViewModel.class.getSimpleName(), "onClickCancel");

        Context context = view.getContext();
        closeActivity(context);
    }

    private void closeActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("addIngredientClose", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }



    @InverseBindingMethods({
            @InverseBindingMethod(type = AppCompatSpinner.class, attribute = "android:selectedItemPosition"),
    })
    public class PizzaObservable extends BaseObservable {

        public String[] types = null;

        public String type = null;

        public String pizzaName = "";

        @Bindable
        public String getPizzaName() {
            return pizzaName;
        }

        public void setPizzaName(String pizzaName) {
            this.pizzaName = pizzaName;
            notifyPropertyChanged(BR.pizzaName);
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
