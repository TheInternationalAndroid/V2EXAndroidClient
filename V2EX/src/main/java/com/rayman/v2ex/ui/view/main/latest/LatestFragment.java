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

package com.rayman.v2ex.ui.view.main.latest;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.mvvm.lib.view.base.page.BaseDIFragment;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.mvvm.lib.widget.anotations.RequestType;
import com.rayman.v2ex.app.ParaKeys;
import com.rayman.v2ex.databinding.FragmentLatestBinding;
import com.ray.mvvm.lib.model.model.member.MemberBaseEntity;
import com.ray.mvvm.lib.model.model.node.NodeEntity;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.account.AccountActivity;
import com.rayman.v2ex.ui.view.node.NodeActivity;
import com.rayman.v2ex.ui.view.topic.TopicActivity;
import com.rayman.v2ex.viewmodel.main.LatestFragVM;
import com.rayman.v2ex.viewmodel.main.LatestFragVMModule;

import javax.inject.Inject;

public class LatestFragment extends BaseDIFragment implements LatestFragContract.View {

    @Inject LatestFragVM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLatestBinding binding = FragmentLatestBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void buildComp() {
        DaggerLatestFragContract_Comp.builder()
                .fragmentComp(fragmentComp())
                .latestFragVMModule(new LatestFragVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.initiallyReq(RequestType.CONTENT_LOADING);
    }

    @Override
    public void onMemberClicked(MemberBaseEntity memberBaseEntity) {
        Intent intent = new Intent(getActivity(), AccountActivity.class);
        intent.putExtra(ParaKeys.MEMBER_BASE_KEY, memberBaseEntity);
        intent(intent);
    }

    @Override
    public void onTopicCliced(TopicEntity topicEntity) {
        Intent intent = new Intent(getActivity(), TopicActivity.class);
        intent.putExtra(ParaKeys.TOPIC_KEY, topicEntity);
        intent(intent);
    }

    @Override
    public void onNodeClicked(NodeEntity nodeEntity) {
        Intent intent = new Intent(getActivity(), NodeActivity.class);
        intent.putExtra(ParaKeys.NODE_KEY, nodeEntity);
        intent(intent);
    }

}
