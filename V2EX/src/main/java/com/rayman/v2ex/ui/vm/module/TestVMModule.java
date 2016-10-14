/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 14 Oct 2016 16:37:22 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 14 Oct 2016 16:37:22 +0800.
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

package com.rayman.v2ex.ui.vm.module;

import com.ray.mvvm.lib.di.scope.PerActivity;
import com.rayman.v2ex.ui.contract.TestContract;
import com.rayman.v2ex.ui.presenter.TestP;
import com.rayman.v2ex.ui.vm.TestVM;

import dagger.Module;
import dagger.Provides;

@Module
public class TestVMModule {

    private TestContract.View view;

    public TestVMModule(TestContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    TestVM provideVM(TestP presenter) {
        return new TestVM(presenter, view);
    }

}
