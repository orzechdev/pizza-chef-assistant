package com.pizzachefassistant.dependencies.modules;

import android.arch.lifecycle.ViewModel;

import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.ui.main.IngredientsViewModel;
import com.pizzachefassistant.ui.main.OrdersViewModel;
import com.pizzachefassistant.ui.main.PizzasViewModel;
import com.pizzachefassistant.ui.utils.ViewModelFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import javax.inject.Provider;

import dagger.MapKey;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @MapKey
    @interface ViewModelKey {
        Class<? extends ViewModel> value();
    }

    @Provides
    ViewModelFactory viewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> providerMap) {
        return new ViewModelFactory(providerMap);
    }

    @Provides
    @IntoMap
    @ViewModelKey(OrdersViewModel.class)
    ViewModel ordersViewModel(MainRepository mainRepository) {
        return new OrdersViewModel(mainRepository);
    }

    @Provides
    @IntoMap
    @ViewModelKey(PizzasViewModel.class)
    ViewModel pizzasViewModel(MainRepository mainRepository) {
        return new PizzasViewModel(mainRepository);
    }

    @Provides
    @IntoMap
    @ViewModelKey(IngredientsViewModel.class)
    ViewModel ingredientsViewModel(MainRepository mainRepository) {
        return new IngredientsViewModel(mainRepository);
    }

}