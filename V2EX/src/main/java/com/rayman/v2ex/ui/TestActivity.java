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

package com.rayman.v2ex.ui;

import android.os.Bundle;

import com.rayman.v2ex.databinding.ActivityTestBinding;
import com.rayman.v2ex.ui.contract.DaggerTestContract_Comp;
import com.rayman.v2ex.ui.vm.TestVM;
import com.rayman.v2ex.ui.vm.module.TestVMModule;
import com.rayman.v2ex.ui.contract.TestContract;
import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;

import javax.inject.Inject;

import com.rayman.v2ex.R;

public class TestActivity extends BaseDIActivity implements TestContract.View {

    @Inject TestVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestBinding binding = bindLayout(R.layout.activity_test);
        binding.setViewModel(viewModel);
    }


    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTestContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .testVMModule(new TestVMModule(this))
                .build()
                .inject(this);

    }
}
