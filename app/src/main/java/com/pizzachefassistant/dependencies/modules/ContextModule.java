package com.pizzachefassistant.dependencies.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    public Context context(){ return context.getApplicationContext(); }
}