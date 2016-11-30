package com.ray.sample.v2ex.view.template.vm.module;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.sample.v2ex.view.template.contract.BasicTempContract;
import com.ray.sample.v2ex.view.template.presenter.BasicTempP;
import com.ray.sample.v2ex.view.template.vm.BasicTempVM;

import dagger.Module;
import dagger.Provides;

@Module
public class BasicTempVMModule {

    private BasicTempContract.View view;

    public BasicTempVMModule(BasicTempContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    BasicTempVM provideVM(BasicTempP presenter) {
        return new BasicTempVM(presenter, view);
    }

}
