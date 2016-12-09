/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 12 Nov 2016 22:35:40 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 12 Nov 2016 22:35:40 +0800.
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
import android.view.View;

import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.mock.contract.DaggerWrapperListSampleContract_Comp;
import com.ray.sample.v2ex.view.mock.contract.WrapperListSampleContract;
import com.ray.sample.v2ex.view.mock.vm.WrapperListSampleVM;
import com.ray.sample.v2ex.view.mock.vm.module.WrapperListSampleVMModule;

import javax.inject.Inject;

public class WrapperListSampleActivity extends BaseDIActivity implements WrapperListSampleContract.View {

    @Inject WrapperListSampleVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_wrapper_list_sample, viewModel);
        viewModel.startRequest();
    }

    @Override
    public void buildComp() {
        DaggerWrapperListSampleContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .wrapperListSampleVMModule(new WrapperListSampleVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onItemClick(int position, View view, TestEntity testEntity) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TestEntity.KEY, testEntity);
        intent(PageSampleActivity.class, bundle);
    }
}
