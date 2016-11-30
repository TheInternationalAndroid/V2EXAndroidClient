package com.ray.sample.v2ex.view.template.vm.module;

import com.ray.mvvm.lib.di.scope.PerFragment;
import com.ray.sample.v2ex.view.template.contract.RayBasicContract;
import com.ray.sample.v2ex.view.template.presenter.RayBasicP;
import com.ray.sample.v2ex.view.template.vm.RayBasicVM;

import dagger.Module;
import dagger.Provides;

@Module
public class RayBasicVMModule {

    private RayBasicContract.View view;

    public RayBasicVMModule(RayBasicContract.View view) {
        this.view = view;
    }

    @Provides
    @PerFragment
    RayBasicVM provideVM(RayBasicP presenter) {
        return new RayBasicVM(presenter, view);
    }

}
