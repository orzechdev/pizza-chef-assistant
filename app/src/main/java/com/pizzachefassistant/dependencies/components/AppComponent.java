package com.pizzachefassistant.dependencies.components;

import android.app.Application;

import com.pizzachefassistant.App;
import com.pizzachefassistant.dependencies.modules.DatabaseModule;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;
import com.pizzachefassistant.ui.utils.ViewModelFactory;
import com.pizzachefassistant.dependencies.modules.ViewModelModule;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.database.MainDatabase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DatabaseModule.class, RepositoryModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(Application application);
    void inject(App app);
}