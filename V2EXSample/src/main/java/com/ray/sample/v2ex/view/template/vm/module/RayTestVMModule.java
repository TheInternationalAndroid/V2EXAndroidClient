package com.ray.sample.v2ex.view.template.vm.module;

import com.ray.mvvm.lib.di.scope.PerFragment;
import com.ray.sample.v2ex.view.template.RayTestFragment;
import com.ray.sample.v2ex.view.template.presenter.RayTestP;
import com.ray.sample.v2ex.view.template.vm.RayTestVM;

import dagger.Module;
import dagger.Provides;

@Module
public class RayTestVMModule {

    private RayTestFragment fragment;

    public RayTestVMModule(RayTestFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    RayTestVM provideVM(RayTestP presenter) {
        RayTestVM viewModel = new RayTestVM(presenter, fragment);
        fragment.setViewModel(viewModel);
        return viewModel;
    }

}
