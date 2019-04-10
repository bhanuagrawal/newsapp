package com.ultimatix.cashrich;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ultimatix.cashrich.di.components.DaggerMainAppComponent;
import com.ultimatix.cashrich.di.modules.MainAppModule;
import com.ultimatix.cashrich.di.components.MainAppComponent;

public class Myapp extends Application {

    MainAppComponent mainAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainAppComponent = DaggerMainAppComponent.builder()
                .mainAppModule(new MainAppModule(this))
                .build();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService( CONNECTIVITY_SERVICE );
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public MainAppComponent getMainAppComponent() {
        return mainAppComponent;
    }
}
