/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 4:52 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: AccountPageAdapter.
 * Author: Lena; Last Modified: 1/20/16 4:52 PM.
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

package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.ui.adapter.list.viewholder.BaseViewHolder;
import com.rayman.v2ex.databinding.ListCellTopicBinding;
import com.rayman.v2ex.databinding.ListHeaderUserDetailBinding;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.viewmodel.account.AccountHeaderCellVM;
import com.rayman.v2ex.viewmodel.topic.TopicCellVM;

import java.util.List;

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
public class AccountPageAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_TOPIC = 1;

    private List<TopicEntity> topicEntities;
    private MemberEntity memberEntity;

    public AccountPageAdapter() {
    }

    @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        switch (viewType) {
            case VIEW_HEADER:
                binding = ListHeaderUserDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                break;
            default:
            case VIEW_TOPIC:
                binding = ListCellTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                break;
        }
        return new BaseViewHolder(binding);
    }

    @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_HEADER:
                holder.bindData(new AccountHeaderCellVM(memberEntity));
                break;
            default:
            case VIEW_TOPIC:
                holder.bindData(new TopicCellVM(getItem(position - 1)));
                break;
        }
    }

    @Override public int getItemCount() {
        return topicEntities == null ? 1 : topicEntities.size() + 1;
    }

    public void setTopicEntities(List<TopicEntity> topicEntities) {
        this.topicEntities = topicEntities;
        notifyDataSetChanged();
    }

    public void setMemberEntity(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
        notifyItemChanged(0);
    }

    public TopicEntity getItem(int position) {
        return topicEntities == null ? null : topicEntities.get(position);
    }

    @Override public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_TOPIC;
        }
    }
}
