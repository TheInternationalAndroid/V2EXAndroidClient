package com.rayman.v2ex.viewmodel.test;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.di.modules.LayoutManagerModule;
import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.test.api.TestApiP;
import com.rayman.v2ex.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

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
@Module(includes = {LayoutManagerModule.class})
public class TestApiVMModule {

    @Provides
    @PerActivity
    TestApiVM provideApiModel(TestApiP presenter, @Named(ListType.VERTICAL) RecyclerView.LayoutManager layoutManager) {
        return new TestApiVM(presenter, layoutManager);
    }

}
