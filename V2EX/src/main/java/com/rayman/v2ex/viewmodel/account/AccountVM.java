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

package com.rayman.v2ex.viewmodel.account;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.ray.mvvm.lib.model.http.ExSubscriber;
import com.ray.mvvm.lib.viewmodel.BaseListVM;
import com.ray.mvvm.lib.widget.anotations.RequestType;
import com.rayman.v2ex.BR;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.AccountPageAdapter;
import com.rayman.v2ex.ui.view.account.AccountContract;
import com.rayman.v2ex.ui.view.account.AccountP;


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
public class AccountVM extends BaseListVM<AccountContract.Presneter, AccountContract.View, TopicEntity> {

    private MemberEntity member;
    private AccountPageAdapter adapter;

    public AccountVM(AccountP presenter, AccountContract.View view, RecyclerView.LayoutManager layoutManager, AccountPageAdapter accountPageAdapter) {
        super(presenter, view, layoutManager, accountPageAdapter);
        adapter = new AccountPageAdapter(view);
    }

    @Override
    protected void exeRequest() {
        String userName = member.getUsername();
        presenter.requestTopicList(userName, this);
        requestMemberDetail(userName);
    }

    private void requestMemberDetail(String userName) {
        presenter.requestMemberDetail(userName, new ExSubscriber<MemberEntity>() {
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
        initiallyReq(RequestType.SILENT);
    }

}
