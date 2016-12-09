package com.ray.sample.v2ex.view.template.vm.module;

import com.ray.mvvm.lib.di.scope.PerFragment;
import com.ray.sample.v2ex.view.template.RayBasicFragment;
import com.ray.sample.v2ex.view.template.presenter.RayBasicP;
import com.ray.sample.v2ex.view.template.vm.RayBasicVM;

import dagger.Module;
import dagger.Provides;

@Module
public class RayBasicVMModule {

    private RayBasicFragment fragment;

    public RayBasicVMModule(RayBasicFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    RayBasicVM provideVM(RayBasicP presenter) {
        RayBasicVM viewModel = new RayBasicVM(presenter, fragment);
        fragment.setViewModel(viewModel);
        return viewModel;
    }

}
