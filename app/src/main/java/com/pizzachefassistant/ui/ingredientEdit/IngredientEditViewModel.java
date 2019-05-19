package com.pizzachefassistant.ui.ingredientEdit;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
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
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.pizzachefassistant.App;
import com.pizzachefassistant.BR;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.ui.IngredientEditActivity;
import com.pizzachefassistant.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

//@InverseBindingMethods({
//        @InverseBindingMethod(type = Spinner.class, attribute = "android:selectedItemPosition"),
//})
public class IngredientEditViewModel extends AndroidViewModel {

    public IngredientObservable ingredientObservable;

    private MainRepository mainRepository;

    public LiveData<List<Ingredient>> ingredients;
    public String[] ingredientsArray;

    public IngredientEditViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        ingredientObservable = new IngredientObservable();

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

                ingredientObservable.setTypes(ingredientsArray);
            }
        });
    }

    private void saveIngredient(Ingredient ingredient, int addedAmount) {
        ingredient.amount = ingredient.amount + addedAmount;

        mainRepository.saveIngredient(ingredient);
    }

    public void onClickSave(View view) {
        Log.i(IngredientEditViewModel.class.getSimpleName(), "onClickSave");

        List<Ingredient> ingredientList = ingredients.getValue();
        if (ingredientList != null) {
            Ingredient ingredient = ingredients.getValue().get(ingredientObservable.selectedTypePosition);

            int addedAmount = Integer.valueOf(ingredientObservable.amount);
            Log.i(IngredientEditViewModel.class.getSimpleName(), "onClickSave" + ingredientObservable.amount);

            saveIngredient(ingredient, addedAmount);

            Context context = view.getContext();
            closeActivity(context);
        }
    }

    public void onClickCancel(View view) {
        Log.i(IngredientEditViewModel.class.getSimpleName(), "onClickCancel");

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
    public class IngredientObservable extends BaseObservable {

        public String[] types = null;

        public String type = null;

        @Bindable
        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
            notifyPropertyChanged(BR.amount);
        }

        public String amount = "";

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
