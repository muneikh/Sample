package com.muneikh.driverclient.dagger.component;

import com.muneikh.driverclient.dagger.module.AppModule;
import com.muneikh.driverclient.dagger.module.NetModule;
import com.muneikh.driverclient.viewmodel.DriverViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(DriverViewModel viewModel);
}
