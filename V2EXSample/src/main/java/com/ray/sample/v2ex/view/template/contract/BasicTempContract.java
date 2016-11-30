package com.ray.sample.v2ex.view.template.contract;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.template.vm.module.BasicTempVMModule;
import com.ray.sample.v2ex.view.template.BasicTempActivity;

import dagger.Component;

public interface BasicTempContract {

    @PerActivity
    @Component(modules = {BasicTempVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(BasicTempActivity activity);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {

    }

}
