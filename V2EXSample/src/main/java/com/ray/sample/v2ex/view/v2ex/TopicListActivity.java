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

package com.ray.sample.v2ex.view.v2ex;

import android.os.Bundle;
import android.view.View;

import com.ray.mvvm.lib.model.model.member.MemberBaseEntity;
import com.ray.mvvm.lib.model.model.reply.ReplyEntity;
import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.databinding.ActivityTopicListBinding;
import com.ray.sample.v2ex.view.v2ex.contract.DaggerTopicListContract_Comp;
import com.ray.sample.v2ex.view.v2ex.contract.TopicListContract;
import com.ray.sample.v2ex.view.v2ex.vm.TopicListVM;
import com.ray.sample.v2ex.view.v2ex.vm.module.TopicListVMModule;

import javax.inject.Inject;

public class TopicListActivity extends BaseDIActivity implements TopicListContract.View {

    @Inject TopicListVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTopicListBinding binding = bindLayout(R.layout.activity_topic_list);
        binding.setViewModel(viewModel);
    }
    
    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTopicListContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .topicListVMModule(new TopicListVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onReplyClicked(ReplyEntity replyEntity) {

    }

    @Override
    public void onItemClick(int position, View view, ReplyEntity replyEntity) {

    }

    @Override
    public void onAvatarClicked(MemberBaseEntity memberEntity) {

    }
}
