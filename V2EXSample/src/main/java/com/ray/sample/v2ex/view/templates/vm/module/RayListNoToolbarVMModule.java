package com.ray.sample.v2ex.view.templates.vm.module;

import android.support.v7.widget.LinearLayoutManager;

import com.ray.mvvm.lib.di.modules.LayoutManagerModule;
import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.widget.anotations.ListType;
import com.ray.sample.v2ex.view.templates.contract.RayListNoToolbarContract;
import com.ray.sample.v2ex.view.templates.presenter.RayListNoToolbarP;
import com.ray.sample.v2ex.view.templates.vm.RayListNoToolbarVM;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = LayoutManagerModule.class)
public class RayListNoToolbarVMModule {

    private RayListNoToolbarContract.View view;

    public RayListNoToolbarVMModule(RayListNoToolbarContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    RayListNoToolbarVM provideVM(RayListNoToolbarP presenter, @Named(ListType.VERTICAL) LinearLayoutManager layoutManager) {
        return new RayListNoToolbarVM(presenter, view, layoutManager, null);
    }

}