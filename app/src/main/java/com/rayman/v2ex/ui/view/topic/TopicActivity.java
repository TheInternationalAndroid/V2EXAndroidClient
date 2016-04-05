/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 3:11 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicActivity.
 * Author: Lena; Last Modified: 1/20/16 3:11 PM.
 * This file is originally created by Lena.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rayman.v2ex.ui.view.topic;

import android.content.Intent;
import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.app.ParaKeys;
import com.rayman.v2ex.databinding.ActivityTopicBinding;
import com.rayman.v2ex.model.model.member.MemberBaseEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.account.AccountActivity;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
import com.rayman.v2ex.viewmodel.topic.TopicVM;
import com.rayman.v2ex.viewmodel.topic.TopicVMModule;

import javax.inject.Inject;

public class TopicActivity extends BaseDIActivity implements TopicContract.View {

    @Inject
    TopicVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTopicBinding binding = bindLayout(R.layout.activity_topic);
        TopicEntity topicEntity = getIntent().getParcelableExtra(ParaKeys.TOPIC_KEY);
        if (topicEntity != null) {
            setTitle(topicEntity.getTitle());
            binding.setViewModel(viewModel);
            viewModel.init(topicEntity);
        }
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTopicContract_TopicComp.builder()
                .activityComp(getActivityComp())
                .topicVMModule(new TopicVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onMemberClicked(MemberBaseEntity memberBaseEntity) {
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra(ParaKeys.MEMBER_BASE_KEY, memberBaseEntity);
        intent(intent);
    }

}
