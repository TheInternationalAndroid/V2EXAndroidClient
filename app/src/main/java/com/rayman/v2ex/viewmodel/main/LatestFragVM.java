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

package com.rayman.v2ex.viewmodel.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rayman.v2ex.model.http.LSubscriber;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.TopicListAdapter;
import com.rayman.v2ex.ui.view.main.latest.LatestFragContract;
import com.rayman.v2ex.ui.view.main.latest.LatestFragP;
import com.rayman.v2ex.viewmodel.BaseSwipStateVM;
import com.rayman.v2ex.widget.anotations.PageState;
import com.rayman.v2ex.widget.anotations.RequestType;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:56
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
public class LatestFragVM extends BaseSwipStateVM<LatestFragContract.Presenter, LatestFragContract.View> {

    private TopicListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public LatestFragVM(LatestFragP presenter, LatestFragContract.View view, RecyclerView.LayoutManager layoutManager) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new TopicListAdapter(view);
    }

    @Override
    public void onRetryClicked(View view) {
        requestLatestTopic(RequestType.CONTENT_LOADING);
    }

    @Override
    public void onRefresh() {
        requestLatestTopic(RequestType.SWIP_REFRESH);
    }

    public TopicListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void requestLatestTopic(@RequestType int requestType) {
        presenter.requestLatestList(new LSubscriber<List<TopicEntity>>() {

            @Override
            public void onStart() {
                super.onStart();
                controlStartState(requestType);
            }

            @Override
            public void onNext(List<TopicEntity> respEntity) {
                controlSuccessState(requestType);
                if (respEntity.size() > 0) {
                    setState(PageState.CONTENT);
                    adapter.setList(respEntity);
                } else {
                    setState(PageState.EMPTY);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                controlErrorState(requestType, throwable.getMessage());
            }
        });
    }
}
