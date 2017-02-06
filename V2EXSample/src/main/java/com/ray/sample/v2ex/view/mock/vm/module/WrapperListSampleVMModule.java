/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.view.mock.vm.module;

import android.support.v7.widget.LinearLayoutManager;

import com.ray.mvvm.lib.di.modules.LayoutManagerModule;
import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.widget.anotations.ListType;
import com.ray.sample.v2ex.adapter.TestListAdapter;
import com.ray.sample.v2ex.view.mock.contract.WrapperListSampleContract;
import com.ray.sample.v2ex.view.mock.presenter.WrapperListSampleP;
import com.ray.sample.v2ex.view.mock.vm.WrapperListSampleVM;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {LayoutManagerModule.class})
public class WrapperListSampleVMModule {

    private WrapperListSampleContract.View view;

    public WrapperListSampleVMModule(WrapperListSampleContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    WrapperListSampleVM provideVM(WrapperListSampleP presenter, @Named(ListType.VERTICAL)LinearLayoutManager layoutManager) {
        return new WrapperListSampleVM(presenter, view,layoutManager,new TestListAdapter(view));
    }

}
