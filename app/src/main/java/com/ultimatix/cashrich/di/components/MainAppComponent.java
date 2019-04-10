package com.ultimatix.cashrich.di.components;


import com.ultimatix.cashrich.data.NewsRepo;
import com.ultimatix.cashrich.di.modules.MainAppModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {MainAppModule.class})
public interface MainAppComponent {
    void inject(NewsRepo newsRepo);
}
