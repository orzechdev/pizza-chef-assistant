package com.pizzachefassistant.ui.pizzaAdd;

import android.app.Application;
import android.app.Dialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.pizzachefassistant.App;
import com.pizzachefassistant.BR;
import com.pizzachefassistant.databinding.AddIngredientToPizzaBinding;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.model.Ingredient;
import com.pizzachefassistant.repository.model.Pizza;
import com.pizzachefassistant.repository.model.PizzaIngredient;
import com.pizzachefassistant.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class PizzaAddViewModel extends AndroidViewModel {

    public PizzaObservable pizzaObservable;

    private MainRepository mainRepository;

    public LiveData<List<Ingredient>> ingredients;
    public String[] ingredientsArray;

    public IngredientObservable currentlyAddedIngredientObservable;
    public List<IngredientObservable> pizzaIngredientsObservables;

    private Dialog addIngredientDialog;

    public PizzaAddViewModel(@NonNull Application application) {
        super(application);
        mainRepository = ((App)application).getComponent().getRepository();

        pizzaObservable = new PizzaObservable();
        currentlyAddedIngredientObservable = new IngredientObservable();

        mapLiveDataFromRepo();
    }

    private void mapLiveDataFromRepo() {
        pizzaIngredientsObservables = new ArrayList<>();

        ingredients = mainRepository.getIngredientList();
//        ingredientsArray = new ArrayList<>();

        ingredients.observeForever(ingredients -> {
//            ingredientsArray.clear();
            if (ingredients != null) {
                String[] ingredientsStrings = StreamSupport.stream(ingredients).map(ingredient -> ingredient.ingredientName).toArray(String[]::new);//.collect(Collectors.toList());
//                ingredientsArray.addAll(ingredientsStrings);
                ingredientsArray = ingredientsStrings;

                currentlyAddedIngredientObservable.setTypes(ingredientsArray);
            }
        });
    }

    private void addPizza(String name, String cookingInstruction, String price, Ingredient ingredient, int neededAmount) {
        mainRepository.addPizza(name, cookingInstruction, price, ingredient, neededAmount);
    }

    public void onClickSave(View view) {
        Log.i(PizzaAddViewModel.class.getSimpleName(), "onClickSave");

        List<Ingredient> ingredientList = ingredients.getValue();
        if (ingredientList != null) {
            /**
             * TODO: ...
             */
//            Ingredient ingredient = ingredients.getValue().get(pizzaObservable.selectedTypePosition);

            String pizzaName = pizzaObservable.pizzaName;
            String cookingInstruction = pizzaObservable.cookingInstruction;
            String price = pizzaObservable.price;
            Log.i(PizzaAddViewModel.class.getSimpleName(), "onClickSave" + pizzaObservable.pizzaName);

            /**
             * TODO: ...
             */
//            addPizza(pizzaName, cookingInstruction, price, ingredient, 5);
//            addPizzaWithIngredients(pizzaName, cookingInstruction, price, ingredient, 5);

            List<PizzaIngredient> pizzaIngredients = StreamSupport.stream(pizzaIngredientsObservables).map(pizzaIngredientsObservable -> {
                return new PizzaIngredient(
                    pizzaIngredientsObservable.selectedTypePosition,
                    Integer.parseInt(pizzaIngredientsObservable.neededAmount)
                );
            }).collect(Collectors.toList());

            mainRepository.addPizzaWithIngredients(pizzaName, cookingInstruction, price, pizzaIngredients);

            Context context = view.getContext();
            closeActivity(context);
        }
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

    public void onAddIngredientButtonClick(View view) {
        addIngredientDialog = new Dialog(view.getContext());
        AddIngredientToPizzaBinding binding = AddIngredientToPizzaBinding.inflate(LayoutInflater.from(view.getContext()));
        binding.setViewModel(this);
        currentlyAddedIngredientObservable = new IngredientObservable();
        currentlyAddedIngredientObservable.setTypes(ingredientsArray);
        binding.setObservable(currentlyAddedIngredientObservable);
        addIngredientDialog.setContentView(binding.getRoot());
        addIngredientDialog.show();
    }

    public void onAddIngredientSaveButtonClick(View view) {
//        int ingredientType = currentlyAddedIngredientObservable.selectedTypePosition;
//        int neededAmount = Integer.parseInt(currentlyAddedIngredientObservable.neededAmount);
//
//        PizzaIngredient pizzaIngredient = new PizzaIngredient()

        pizzaIngredientsObservables.add(currentlyAddedIngredientObservable);

        addIngredientDialog.dismiss();
    }

    public class PizzaObservable extends BaseObservable {

        public String pizzaName = "";
        public String cookingInstruction = "";
        public String price = "";

        @Bindable
        public String getPizzaName() {
            return pizzaName;
        }

        public void setPizzaName(String pizzaName) {
            this.pizzaName = pizzaName;
            notifyPropertyChanged(BR.pizzaName);
        }

        @Bindable
        public String getCookingInstruction() {
            return cookingInstruction;
        }

        public void setCookingInstruction(String cookingInstruction) {
            this.cookingInstruction = cookingInstruction;
            notifyPropertyChanged(BR.cookingInstruction);
        }

        @Bindable
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
            notifyPropertyChanged(BR.price);
        }

    }

//    @InverseBindingMethods({
//            @InverseBindingMethod(type = AppCompatSpinner.class, attribute = "android:selectedItemPosition"),
//    })
    public class IngredientObservable extends BaseObservable {

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
