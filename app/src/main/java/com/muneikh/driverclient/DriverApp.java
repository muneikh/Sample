package com.muneikh.driverclient;

import android.app.Application;

import com.muneikh.driverclient.dagger.component.DaggerNetComponent;
import com.muneikh.driverclient.dagger.component.NetComponent;
import com.muneikh.driverclient.dagger.module.AppModule;
import com.muneikh.driverclient.dagger.module.NetModule;

import timber.log.Timber;

public class DriverApp extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("Your base url here"))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
