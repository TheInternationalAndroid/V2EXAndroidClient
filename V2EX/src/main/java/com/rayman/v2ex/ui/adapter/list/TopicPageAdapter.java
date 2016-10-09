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
import com.rayman.v2ex.databinding.ListCellReplyBinding;
import com.rayman.v2ex.databinding.ListHeaderTopicBinding;
import com.ray.mvvm.lib.model.model.reply.ReplyEntity;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.common.IMemberCellView;
import com.rayman.v2ex.viewmodel.topic.ReplyCellVM;
import com.rayman.v2ex.viewmodel.topic.TopicHeaderVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 5:34 PM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class TopicPageAdapter extends BaseListAdapter<ReplyEntity> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_CELL = 1;
    public static final int HEADER_COUNT = 1;
    private TopicEntity topicEntity;
    private IMemberCellView memberCellView;

    public TopicPageAdapter(IMemberCellView memberCellView) {
        this.memberCellView = memberCellView;
    }

    @Override
    public int getHeaderCount() {
        return HEADER_COUNT;
    }

    @Override
    protected ViewDataBinding createBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return ListHeaderTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            default:
            case VIEW_CELL:
                return ListCellReplyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_CELL;
        }
    }

    @Override
    protected Object createViewModel(int viewType, int position) {
        switch (getItemViewType(position)) {
            case VIEW_HEADER:
                return new TopicHeaderVM(topicEntity, memberCellView);
            default:
            case VIEW_CELL:
                return new ReplyCellVM(getItem(position), position, memberCellView);
        }
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
        notifyItemChanged(0);
    }
}
