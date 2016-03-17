/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:56 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: LatestFragVM.
 * Author: Lena; Last Modified: 1/19/16 3:56 PM.
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

package com.rayman.v2ex.viewmodel.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rayman.v2ex.model.http.callback.ReqCallback;
import com.rayman.v2ex.model.http.event.ErrorEvent;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.main.LatestFragP;
import com.rayman.v2ex.ui.adapter.list.TopicListAdapter;
import com.rayman.v2ex.ui.view.main.ILatestFragView;
import com.rayman.v2ex.viewmodel.BaseStateVM;
import com.rayman.v2ex.widget.anotations.PageState;

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
public class LatestFragVM extends BaseStateVM<LatestFragP> {

    private TopicListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ILatestFragView latestFragView;

    public LatestFragVM(LatestFragP presenter, ILatestFragView latestFragView, RecyclerView.LayoutManager layoutManager) {
        super(presenter);
        this.layoutManager = layoutManager;
        this.latestFragView = latestFragView;
        adapter = new TopicListAdapter(latestFragView);
    }

    @Override public void onRetryClicked(View view) {
        requestLatestTopic();
    }

    public TopicListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void requestLatestTopic() {
        presenter.latest(new ReqCallback<List<TopicEntity>>() {
            @Override public void onReqStart() {
                setState(PageState.LOADING);
            }

            @Override public void onNetResp(List<TopicEntity> response) {
                if (response.size() > 0) {
                    setState(PageState.CONTENT);
                    adapter.setList(response);
                } else {
                    setState(PageState.EMPTY);
                }
            }

            @Override public void onError(ErrorEvent errorEvent) {
                showError(PageState.ERROR, errorEvent.getMessage());
            }
        });
    }

}
