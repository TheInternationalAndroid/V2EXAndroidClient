package com.ray.sample.v2ex.view.template.vm.module;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.sample.v2ex.view.template.contract.RayTest2Contract;
import com.ray.sample.v2ex.view.template.presenter.RayTest2P;
import com.ray.sample.v2ex.view.template.vm.RayTest2VM;

import dagger.Module;
import dagger.Provides;

@Module
public class RayTest2VMModule {

    private RayTest2Contract.View view;

    public RayTest2VMModule(RayTest2Contract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    RayTest2VM provideVM(RayTest2P presenter) {
        return new RayTest2VM(presenter, view);
    }

}