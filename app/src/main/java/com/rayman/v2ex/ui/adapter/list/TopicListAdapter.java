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

package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.ListCellTopicBinding;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.base.BaseListAdapter;
import com.rayman.v2ex.ui.adapter.list.base.CellVM;
import com.rayman.v2ex.ui.view.ITopicCellView;
import com.rayman.v2ex.viewmodel.topic.TopicCellVM;

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
public class TopicListAdapter extends BaseListAdapter<TopicEntity> {

    private ITopicCellView onTopicCellClicked;

    public TopicListAdapter(ITopicCellView onTopicCellClicked) {
        this.onTopicCellClicked = onTopicCellClicked;
    }

    @Override
    protected ViewDataBinding buildBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return ListCellTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    protected CellVM<TopicEntity> getViewModel(int position) {
        return new TopicCellVM(getItem(position), onTopicCellClicked);
    }


}
