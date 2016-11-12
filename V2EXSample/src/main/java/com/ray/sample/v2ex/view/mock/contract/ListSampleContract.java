/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 12 Nov 2016 21:29:47 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 12 Nov 2016 21:29:47 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
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
import com.ray.mvvm.lib.view.adapter.OnItemClick;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.mock.ListSampleActivity;
import com.ray.sample.v2ex.view.mock.vm.module.ListSampleVMModule;

import java.util.List;

import dagger.Component;

public interface ListSampleContract {

    @PerActivity
    @Component(modules = {ListSampleVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(ListSampleActivity activity);
    }

    interface View extends IView, TestEntityCellView {
    }

    interface TestEntityCellView extends OnItemClick<TestEntity> {
    }

    interface Presenter extends IPresenter {
        void requestData(ExObserver<List<TestEntity>> observer);
    }

}
