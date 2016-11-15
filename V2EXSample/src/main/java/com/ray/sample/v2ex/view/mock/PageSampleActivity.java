/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 12 Nov 2016 22:57:00 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 12 Nov 2016 22:57:00 +0800.
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

package com.ray.sample.v2ex.view.mock;

import android.os.Bundle;

import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.databinding.ActivityPageSampleBinding;
import com.ray.sample.v2ex.view.mock.contract.DaggerPageSampleContract_Comp;
import com.ray.sample.v2ex.view.mock.contract.PageSampleContract;
import com.ray.sample.v2ex.view.mock.vm.PageSampleVM;
import com.ray.sample.v2ex.view.mock.vm.module.PageSampleVMModule;

import javax.inject.Inject;

public class PageSampleActivity extends BaseDIActivity implements PageSampleContract.View {

    @Inject PageSampleVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPageSampleBinding binding = bindLayout(R.layout.activity_page_sample);
        binding.setViewModel(viewModel);
        TestEntity testEntity = getIntent().getParcelableExtra(TestEntity.KEY);
        viewModel.init(testEntity);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerPageSampleContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .pageSampleVMModule(new PageSampleVMModule(this))
                .build()
                .inject(this);
    }
}
