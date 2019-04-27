package com.pizzachefassistant;

import android.app.Application;

public class App extends Application {

//    private AppComponent component;

//    @Inject
//    Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
//        component = DaggerAppComponent.builder()
//                .build();
//        getComponent().inject(this); // inject retrofit here
    }

//    public AppComponent getComponent() {
//        return component;
//    }
}