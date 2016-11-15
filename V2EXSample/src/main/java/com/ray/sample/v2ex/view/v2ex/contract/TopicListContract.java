/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 23:40:53 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 23:40:53 +0800.
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

package com.ray.sample.v2ex.view.v2ex.contract;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.http.ExSubscriber;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.sample.v2ex.view.common.v2ex.TopicCellView;
import com.ray.sample.v2ex.view.v2ex.TopicListActivity;
import com.ray.sample.v2ex.view.v2ex.vm.module.TopicListVMModule;

import java.util.List;

import dagger.Component;

public interface TopicListContract {

    @PerActivity
    @Component(modules = {TopicListVMModule.class}, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {
        void inject(TopicListActivity activity);
    }

    interface View extends IView, TopicCellView {
    }

    interface Presenter extends IPresenter {
        void requestTopicList(ExObserver<List<TopicEntity>> observer);

        void findTopicList(ExSubscriber<List<TopicEntity>> observer);
    }

}
