package com.pizzachefassistant.dependencies.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.pizzachefassistant.repository.database.MainDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Singleton
    @Provides
    public MainDatabase mainDatabase(Context context){
        return Room.databaseBuilder(context, MainDatabase.class, "database-main")
                .fallbackToDestructiveMigration()
                .build();
    }

}