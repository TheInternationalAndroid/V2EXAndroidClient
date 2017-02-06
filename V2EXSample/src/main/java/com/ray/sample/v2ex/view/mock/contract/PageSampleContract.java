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

package com.ray.sample.v2ex.view.mock.contract;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.mock.PageSampleActivity;
import com.ray.sample.v2ex.view.mock.vm.module.PageSampleVMModule;

import dagger.Component;

public interface PageSampleContract {

    @PerActivity
    @Component(modules = {PageSampleVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(PageSampleActivity activity);
    }

    interface View extends IView {
    }

    interface Presenter extends IPresenter {
        void requestEntity(ExObserver<TestEntity> observer);
    }

}
