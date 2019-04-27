package com.pizzachefassistant.dependencies.modules;

import com.pizzachefassistant.repository.MainRepository;
import com.pizzachefassistant.repository.database.MainDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    public MainRepository mainRepository(MainDatabase mainDatabase){
        return new MainRepository(mainDatabase);
    }
}
