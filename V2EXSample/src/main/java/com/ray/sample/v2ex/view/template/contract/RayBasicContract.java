package com.ray.sample.v2ex.view.template.contract;

import com.ray.mvvm.lib.di.scope.PerFragment;
import com.ray.mvvm.lib.view.base.comp.FragmentComp;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.template.vm.module.RayBasicVMModule;
import com.ray.sample.v2ex.view.template.RayBasicFragment;

import dagger.Component;

public interface RayBasicContract {

    @PerFragment
    @Component(modules = {RayBasicVMModule.class}, dependencies = FragmentComp.class)
    interface Comp extends FragmentComp {
        void inject(RayBasicFragment fragment);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {

    }

}
