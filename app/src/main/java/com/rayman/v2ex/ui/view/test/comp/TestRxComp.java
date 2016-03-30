package com.rayman.v2ex.ui.view.test.comp;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.ui.view.test.TestRxActivity;
import com.rayman.v2ex.viewmodel.test.TestRxVMModule;

import dagger.Component;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 12:36 PM
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
@Component(modules = {TestRxVMModule.class}, dependencies = ActivityComp.class)
public interface TestRxComp extends ActivityComp {

    void inject(TestRxActivity activity);

}
