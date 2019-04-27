package com.pizzachefassistant.dependencies.components;

import android.app.Application;
import android.content.Context;

import com.pizzachefassistant.App;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;
import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.database.MainDatabase;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {RepositoryModule.class})
@Singleton
public interface AppComponent {
    @Singleton
    Context getContext();
    @Singleton
    MainDatabase getDatabase();
    @Singleton
    MainRepository getRepository();

    void inject(Application application);
    void inject(App app);
}