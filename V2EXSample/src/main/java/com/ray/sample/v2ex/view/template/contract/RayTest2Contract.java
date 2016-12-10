package com.ray.sample.v2ex.view.template.contract;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.template.vm.module.RayTest2VMModule;
import com.ray.sample.v2ex.view.template.RayTest2Activity;

import dagger.Component;

public interface RayTest2Contract {

    @PerActivity
    @Component(modules = {RayTest2VMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(RayTest2Activity activity);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {
    }

}
