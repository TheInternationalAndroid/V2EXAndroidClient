/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.view.v2ex.vm;

import android.support.v7.widget.RecyclerView;

import com.ray.mvvm.lib.model.http.ExSubscriber;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.view.adapter.list.base.StateListAdapter;
import com.ray.mvvm.lib.viewmodel.StateListVM;
import com.ray.sample.v2ex.view.v2ex.contract.TopicListContract;

import java.util.List;

public class TopicListVM extends StateListVM<TopicListContract.Presenter, TopicListContract.View, TopicEntity> {

    public TopicListVM(TopicListContract.Presenter presenter, TopicListContract.View view, RecyclerView.LayoutManager layoutManager, StateListAdapter<TopicEntity> adapter) {
        super(presenter, view, layoutManager, adapter);
    }

    @Override
    protected void exeRequest() {
        presenter.requestTopicList(this);
    }

    public void init() {
        presenter.findTopicList(new ExSubscriber<List<TopicEntity>>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<TopicEntity> topicEntities) {
                if (topicEntities != null && topicEntities.size() > 0) {
                    TopicListVM.this.onNext(topicEntities);
                    startRefreshRequestAuto();
                } else {
                    startRequest();
                }
            }
        });
    }

}
