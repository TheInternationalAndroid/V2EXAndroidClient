/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 11:49 AM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotFragment.
 * Author: Lena; Last Modified: 1/19/16 11:48 AM.
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

package com.rayman.v2ex.view.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayman.v2ex.app.ParaKeys;
import com.rayman.v2ex.databinding.FragmentHotBinding;
import com.rayman.v2ex.di.component.view.main.DaggerHotFragComp;
import com.rayman.v2ex.di.modules.vm.main.HotFragVMModule;
import com.rayman.v2ex.model.model.member.MemberBaseEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.IPage;
import com.rayman.v2ex.view.account.AccountActivity;
import com.rayman.v2ex.view.base.BaseDIFragment;
import com.rayman.v2ex.vm.main.HotFragVM;

import javax.inject.Inject;

public class HotFragment extends BaseDIFragment implements IHotFragView {

    @Inject HotFragVM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHotBinding binding = FragmentHotBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.requestHotTopicList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getFragmentComp().refWatchwer().watch(viewModel);
    }

    @Override public void onInject() {
        DaggerHotFragComp.builder()
                .fragmentComp(getFragmentComp())
                .hotFragVMModule(new HotFragVMModule(this))
                .build()
                .inject(this);
    }

    @Override protected IPage getPageCallBack() {
        return viewModel;
    }

    @Override public void onUserClicked(MemberBaseEntity memberBaseEntity) {
        Intent intent = new Intent(getActivity(), AccountActivity.class);
        intent.putExtra(ParaKeys.MEMBER_BASE_KEY, memberBaseEntity);
        intent(intent);
    }

    @Override public void onTopicCliced(TopicEntity topicEntity) {

    }

    @Override public void onNodeClicked(NodeEntity nodeEntity) {

    }
}
