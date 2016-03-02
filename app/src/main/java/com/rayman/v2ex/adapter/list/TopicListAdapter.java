/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 11:20 AM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotTopicListAdapter.
 * Author: Lena; Last Modified: 1/20/16 11:20 AM.
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

package com.rayman.v2ex.adapter.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.adapter.list.viewholder.BaseViewHolder;
import com.rayman.v2ex.databinding.ListCellTopicBinding;
import com.rayman.v2ex.model.topic.TopicEntity;
import com.rayman.v2ex.view.main.OnTopicCellClicked;
import com.rayman.v2ex.vm.topic.TopicCellVM;

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
public class TopicListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<TopicEntity> topicEntities;
    private OnTopicCellClicked onTopicCellClicked;

    public TopicListAdapter(OnTopicCellClicked onTopicCellClicked) {
        this.onTopicCellClicked = onTopicCellClicked;
    }

    @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListCellTopicBinding binding = ListCellTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BaseViewHolder(binding);
    }

    @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindData(new TopicCellVM(getItem(position), onTopicCellClicked));
    }

    @Override public int getItemCount() {
        return topicEntities == null ? 0 : topicEntities.size();
    }

    public void setTopicEntities(List<TopicEntity> topicEntities) {
        this.topicEntities = topicEntities;
        notifyDataSetChanged();
    }

    public TopicEntity getItem(int position) {
        return topicEntities == null ? null : topicEntities.get(position);
    }

}
