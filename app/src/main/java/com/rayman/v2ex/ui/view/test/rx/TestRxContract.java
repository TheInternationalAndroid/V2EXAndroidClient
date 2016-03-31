package com.rayman.v2ex.ui.view.test.rx;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.viewmodel.test.TestRxVMModule;

import dagger.Component;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 3:30 PM
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
public interface TestRxContract {

    @PerActivity
    @Component(modules = {TestRxVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {

        void inject(TestRxActivity activity);

    }

    interface Presenter extends IPresenter {
        void testThread();

        void testEmpty();

        void testNever();

        void testFrom();

        void testFromFuture();

        void testInterval();

        void testJust();

        void testRange();

        void testRepeat();

        void testStartWith();

        void testTimer();

    }

}
