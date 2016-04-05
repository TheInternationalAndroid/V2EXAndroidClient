package com.rayman.v2ex.viewmodel.test;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.di.modules.LayoutManagerModule;
import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.test.rx.TestRxContract;
import com.rayman.v2ex.ui.view.test.rx.TestRxP;
import com.rayman.v2ex.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 12:33 PM
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
@Module(includes = LayoutManagerModule.class)
public class TestRxVMModule {

    private TestRxContract.View view;

    public TestRxVMModule(TestRxContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    TestRxVM provideTestRxVM(@Named(ListType.VERTICAL) RecyclerView.LayoutManager layoutManager, TestRxP presenter) {
        return new TestRxVM(presenter, view, layoutManager);
    }

}
