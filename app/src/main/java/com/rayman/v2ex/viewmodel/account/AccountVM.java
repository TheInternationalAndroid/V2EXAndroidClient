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

package com.rayman.v2ex.viewmodel.account;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.rayman.v2ex.model.http.LSubscriber;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.AccountPageAdapter;
import com.rayman.v2ex.ui.view.account.AccountContract;
import com.rayman.v2ex.ui.view.account.AccountP;
import com.rayman.v2ex.viewmodel.BaseVM;

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
public class AccountVM extends BaseVM<AccountContract.Presneter, AccountContract.View> {

    private MemberEntity member;
    private AccountPageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public AccountVM(RecyclerView.LayoutManager layoutManager, AccountP presenter, AccountContract.View view) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new AccountPageAdapter(view);
    }

    private void requestTopics(String userName) {
        presenter.requestTopicList(userName, new LSubscriber<List<TopicEntity>>() {
            @Override
            public void onNext(List<TopicEntity> respEntity) {
                if (respEntity.size() > 0)
                    adapter.setList(respEntity);
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }

    private void requestMemberDetail(String userName) {
        presenter.requestMemberDetail(userName, new LSubscriber<MemberEntity>() {
            @Override
            public void onNext(MemberEntity respEntity) {
                if (member != null)
                    setMember(respEntity);
            }

            @Override
            public void onError(Throwable throwable) {
            }
        });
    }

    public AccountPageAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Bindable
    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
        notifyPropertyChanged(BR.member);
    }

    public void init(MemberEntity member) {
        if (member == null)
            return;
        this.member = member;
        adapter.setMemberEntity(member);
        requestMemberDetail(member.getUsername());
        requestTopics(member.getUsername());
    }

}
