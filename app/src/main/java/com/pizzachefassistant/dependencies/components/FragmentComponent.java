package com.pizzachefassistant.dependencies.components;

import android.support.v4.app.Fragment;

import com.pizzachefassistant.dependencies.modules.DatabaseModule;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;
import com.pizzachefassistant.repository.database.MainDatabase;
import com.pizzachefassistant.ui.utils.ViewModelFactory;
import com.pizzachefassistant.dependencies.modules.ViewModelModule;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.ui.main.IngredientsFragment;
import com.pizzachefassistant.ui.main.OrdersFragment;
import com.pizzachefassistant.ui.main.PizzasFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModelModule.class, DatabaseModule.class, RepositoryModule.class, ViewModelModule.class})
public interface FragmentComponent {
    MainDatabase getDatabase();
    MainRepository getRepository();
    ViewModelFactory getViewModelFactory();

    void inject(Fragment fragment);
    void inject(OrdersFragment ordersFragment);
    void inject(PizzasFragment pizzasFragment);
    void inject(IngredientsFragment ingredientsFragment);
}