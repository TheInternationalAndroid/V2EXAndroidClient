package com.rayman.v2ex.ui.view.main;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.viewmodel.main.MainActivityVM;
import com.rayman.v2ex.viewmodel.main.MainActivityVMModule;

import dagger.Component;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 3:24 PM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public interface MainContract {

    @PerActivity
    @Component(modules = {MainActivityVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {

        void inject(MainActivity mainActivity);

        MainActivityVM viewModel();
    }

    interface Presenter extends IPresenter {

    }

}
