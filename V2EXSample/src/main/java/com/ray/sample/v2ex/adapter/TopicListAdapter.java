/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 23:42:38 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 23:42:38 +0800.
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

package com.ray.sample.v2ex.adapter;

import android.databinding.BaseObservable;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.view.adapter.list.base.StateListAdapter;
import com.ray.sample.v2ex.databinding.ListCellTopicBinding;
import com.ray.sample.v2ex.view.common.v2ex.TopicCellView;
import com.ray.sample.v2ex.view.v2ex.vm.TopicCellVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 11:20
 * \ ___________________
 * \| Happy New Year!   |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class TopicListAdapter extends StateListAdapter<TopicEntity> {

    private TopicCellView topicCellView;

    public TopicListAdapter(TopicCellView topicCellView) {
        this.topicCellView = topicCellView;
    }

    @Override
    protected ViewDataBinding createBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return ListCellTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    protected BaseObservable createViewModel(RecyclerView.ViewHolder holder, int position, int viewType) {
        return new TopicCellVM(getItem(position), holder, topicCellView);
    }
}
