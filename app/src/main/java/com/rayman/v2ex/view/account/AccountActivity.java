/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 3:08 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: AccountActivity.
 * Author: Lena; Last Modified: 1/20/16 3:08 PM.
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

package com.rayman.v2ex.view.account;

import android.content.Intent;
import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.app.ParaKeys;
import com.rayman.v2ex.databinding.ActivityAccountBinding;
import com.rayman.v2ex.di.component.view.DaggerAccountComp;
import com.rayman.v2ex.di.modules.vm.account.AccountVMModule;
import com.rayman.v2ex.model.member.MemberBaseEntity;
import com.rayman.v2ex.model.member.MemberEntity;
import com.rayman.v2ex.presenter.IPage;
import com.rayman.v2ex.view.base.BaseDIActivity;
import com.rayman.v2ex.vm.account.AccountVM;

import javax.inject.Inject;

public class AccountActivity extends BaseDIActivity {

    @Inject AccountVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        MemberBaseEntity memberBaseEntity = intent.getParcelableExtra(ParaKeys.MEMBER_BASE_KEY);
        ActivityAccountBinding binding = bindLayout(R.layout.activity_account);
        binding.setViewModel(viewModel);
        viewModel.setMember(new MemberEntity(memberBaseEntity));
        viewModel.requestTopics();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityComp().refWatchwer().watch(viewModel);
    }

    @Override
    public void onInject() {
        DaggerAccountComp.builder()
                .activityComp(getActivityComp())
                .accountVMModule(new AccountVMModule())
                .build()
                .inject(this);
    }

    @Override
    protected IPage getPageCallback() {
        return viewModel;
    }
}
