package com.pizzachefassistant;

import android.app.Application;

import com.pizzachefassistant.dependencies.components.AppComponent;
import com.pizzachefassistant.dependencies.components.DaggerAppComponent;
import com.pizzachefassistant.dependencies.modules.ContextModule;
import com.pizzachefassistant.dependencies.modules.DatabaseModule;
import com.pizzachefassistant.dependencies.modules.RepositoryModule;

public class App extends Application {

    private AppComponent component;

//    @Inject
//    Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .databaseModule(new DatabaseModule())
                .repositoryModule(new RepositoryModule())
                .build();
        getComponent().inject(this); // inject retrofit here
    }

    public AppComponent getComponent() {
        return component;
    }
}