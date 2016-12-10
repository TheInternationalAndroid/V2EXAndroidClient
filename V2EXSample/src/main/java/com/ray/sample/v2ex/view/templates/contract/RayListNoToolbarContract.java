package com.ray.sample.v2ex.view.templates.contract;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.templates.vm.module.RayListNoToolbarVMModule;
import com.ray.sample.v2ex.view.templates.RayListNoToolbarActivity;

import dagger.Component;

public interface RayListNoToolbarContract {

    @PerActivity
    @Component(modules = {RayListNoToolbarVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(RayListNoToolbarActivity activity);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {
    }

}
