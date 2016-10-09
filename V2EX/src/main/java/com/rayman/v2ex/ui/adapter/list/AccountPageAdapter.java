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

package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ray.mvvm.lib.view.adapter.list.base.BaseListAdapter;
import com.ray.mvvm.lib.view.adapter.list.base.CellVM;
import com.rayman.v2ex.databinding.ListCellTopicShortBinding;
import com.rayman.v2ex.databinding.ListHeaderUserDetailBinding;
import com.ray.mvvm.lib.model.model.member.MemberEntity;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.common.ITopicShortCellView;
import com.rayman.v2ex.viewmodel.topic.TopicShortCellVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 11:20
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
public class AccountPageAdapter extends BaseListAdapter<TopicEntity> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_TOPIC = 1;
    private static final int HEADER_COUNT = 1;

    private MemberEntity memberEntity;
    private ITopicShortCellView topicShortCellView;

    public AccountPageAdapter(ITopicShortCellView topicShortCellView) {
        this.topicShortCellView = topicShortCellView;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_TOPIC;
        }
    }

    @Override
    protected ViewDataBinding createBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return ListHeaderUserDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            default:
            case VIEW_TOPIC:
                return ListCellTopicShortBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }
    }

    @Override
    protected Object createViewModel(int viewType, int position) {
        switch (viewType) {
            case VIEW_HEADER:
                return new CellVM<>(memberEntity);
            default:
            case VIEW_TOPIC:
                return new TopicShortCellVM(getItem(position), topicShortCellView);
        }
    }

    @Override
    public int getHeaderCount() {
        return HEADER_COUNT;
    }

    public void setMemberEntity(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
        notifyItemChanged(0);
    }

}
