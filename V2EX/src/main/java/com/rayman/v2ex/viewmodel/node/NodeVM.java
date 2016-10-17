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

package com.rayman.v2ex.viewmodel.node;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.ray.mvvm.lib.model.http.ExSubscriber;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.rayman.v2ex.BR;
import com.rayman.v2ex.ui.adapter.list.NodePageAdapter;
import com.rayman.v2ex.ui.view.node.NodeContract;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/7/16
 * Time: 10:23 AM
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
public class NodeVM extends BaseVM<NodeContract.Presenter, NodeContract.View> {

    private final NodePageAdapter adapter;
    private final RecyclerView.LayoutManager layoutManager;

    private NodeEntity nodeEntity;

    public NodeVM(NodeContract.Presenter presenter, NodeContract.View view, RecyclerView.LayoutManager layoutManager) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new NodePageAdapter(view);
    }

    public void init(NodeEntity nodeEntity) {
        setNodeEntity(nodeEntity);
        requestNodeDetail();
        requestTopicList();
    }

    public void setNodeEntity(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
        adapter.setNodeEntity(nodeEntity);
        notifyPropertyChanged(BR.nodeEntity);
    }

    @Bindable
    public NodeEntity getNodeEntity() {
        return nodeEntity;
    }

    public NodePageAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    private void requestNodeDetail() {
        presenter.requestNodeByName(nodeEntity.getName(), new ExSubscriber<NodeEntity>() {

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(NodeEntity nodeEntity) {
                if (nodeEntity != null)
                    setNodeEntity(nodeEntity);
            }
        });
    }

    private void requestTopicList() {
        presenter.requestTopicsByNodeId(nodeEntity.getId(), new ExSubscriber<List<TopicEntity>>() {
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<TopicEntity> topicEntities) {
                if (topicEntities != null && topicEntities.size() > 0) {
                    adapter.setList(topicEntities);
                }
            }
        });
    }

}
