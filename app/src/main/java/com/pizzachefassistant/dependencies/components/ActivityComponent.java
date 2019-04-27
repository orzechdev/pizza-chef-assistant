package com.pizzachefassistant.dependencies.components;

import android.app.Activity;

import com.pizzachefassistant.dependencies.modules.DatabaseModule;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;
import com.pizzachefassistant.ui.utils.ViewModelFactory;
import com.pizzachefassistant.dependencies.modules.ViewModelModule;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.database.MainDatabase;
import com.pizzachefassistant.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoryModule.class, ViewModelModule.class})
public interface ActivityComponent {
    void inject(Activity activity);
    void inject(MainActivity mainActivity);
}