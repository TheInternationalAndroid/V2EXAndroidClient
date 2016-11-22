/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Tue, 15 Nov 2016 08:11:00 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Tue, 15 Nov 2016 08:11:00 +0800.
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

package com.ray.sample.v2ex.view.v2ex;

import android.os.Bundle;
import android.view.View;

import com.ray.mvvm.lib.model.model.member.MemberEntity;
import com.ray.mvvm.lib.model.model.reply.ReplyEntity;
import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.v2ex.contract.DaggerTopicDetailContract_Comp;
import com.ray.sample.v2ex.view.v2ex.contract.TopicDetailContract;
import com.ray.sample.v2ex.view.v2ex.vm.TopicDetailVM;
import com.ray.sample.v2ex.view.v2ex.vm.module.TopicDetailVMModule;

import javax.inject.Inject;

public class TopicDetailActivity extends BaseDIActivity implements TopicDetailContract.View {

    @Inject TopicDetailVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_topic_detail, viewModel);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTopicDetailContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .topicDetailVMModule(new TopicDetailVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onItemClick(int position, View view, ReplyEntity replyEntity) {

    }

    @Override
    public void onAvatarClicked(MemberEntity memberEntity) {

    }
}
