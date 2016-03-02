/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 4:32 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: AccountVM.
 * Author: Lena; Last Modified: 1/20/16 4:32 PM.
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

package com.rayman.v2ex.vm.account;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.adapter.list.AccountPageAdapter;
import com.rayman.v2ex.http.callback.ReqCallback;
import com.rayman.v2ex.http.event.ErrorEvent;
import com.rayman.v2ex.model.member.MemberEntity;
import com.rayman.v2ex.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.account.AccountP;
import com.rayman.v2ex.vm.BaseVM;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 16:32
 * \ ___________________
 * \| Happy New Year!  |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class AccountVM extends BaseVM<AccountP> {

    private MemberEntity member;
    private AccountPageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public AccountVM(AccountPageAdapter adapter, RecyclerView.LayoutManager layoutManager, AccountP presenter) {
        super(presenter);
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    public void requestTopics() {
        presenter.topics(member.getUsername(), new ReqCallback<List<TopicEntity>>() {
            @Override public void onReqStart() {

            }

            @Override public void onNetResp(List<TopicEntity> response) {
                if (response.size() > 0)
                    adapter.setTopicEntities(response);
            }

            @Override public void onError(ErrorEvent errorEvent) {
            }
        });
    }

    public AccountPageAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Bindable public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
        adapter.setMemberEntity(member);
    }

}
