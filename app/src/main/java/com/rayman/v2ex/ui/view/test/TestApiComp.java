package com.rayman.v2ex.ui.view.test;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.viewmodel.test.TestApiModule;
import com.rayman.v2ex.viewmodel.test.TestApiVM;

import dagger.Component;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 3:37 PM
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
@PerActivity
@Component(modules = TestApiModule.class, dependencies = ActivityComp.class)
public interface TestApiComp extends ActivityComp {

    void inject(TestApiActivity testApiActivity);

    TestApiVM viewModel();

}
