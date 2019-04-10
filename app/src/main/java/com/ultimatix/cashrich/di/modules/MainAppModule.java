package com.ultimatix.cashrich.di.modules;


import android.app.Application;

import com.ultimatix.cashrich.data.AppDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class MainAppModule {

    private Application mApplication;

    public MainAppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    AppDatabase providesLauncherDatabase(Application application){
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "launcher-data").build();
        return db;
    }

}
