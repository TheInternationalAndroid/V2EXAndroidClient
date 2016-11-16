/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:56:12 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:56:12 +0800.
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

package com.ray.mvvm.lib.viewmodel;

import android.support.v7.widget.RecyclerView;

import com.ray.mvvm.lib.model.model.ListRespEntity;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.adapter.list.base.ListAdapter;
import com.ray.mvvm.lib.view.base.view.IView;

public abstract class ListRespVM<T extends IPresenter, R extends IView, Q> extends SwipRefreshVM<T, R, ListRespEntity<Q>> {

    private final RecyclerView.LayoutManager layoutManager;
    private ListAdapter<Q> adapter;

    public ListRespVM(T presenter, R view, RecyclerView.LayoutManager layoutManager, ListAdapter<Q> adapter) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        this.adapter = adapter;
        this.adapter.setStateVM(this);
    }

    @Override
    protected final boolean isRespNull(ListRespEntity<Q> data) {
        return data == null || data.getList() == null || data.getList().size() == 0;
    }

    public ListAdapter<Q> getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @Override
    protected void bindResp(ListRespEntity<Q> data) {
        adapter.setList(data.getList());
        layoutManager.scrollToPosition(0);
    }
}
