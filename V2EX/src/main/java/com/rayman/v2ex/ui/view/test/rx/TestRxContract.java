/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 22:22:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 22:22:35 +0800.
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

package com.rayman.v2ex.ui.view.test.rx;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.ray.mvvm.lib.view.base.comp.ActivityComp;
import com.ray.mvvm.lib.view.base.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
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

    interface View extends IView {

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
