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

import android.support.v7.widget.LinearLayoutManager;

import com.ray.mvvm.lib.app.Constants;
import com.ray.mvvm.lib.interfaces.ILoadMore;
import com.ray.mvvm.lib.model.model.ListRespEntity;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.adapter.list.base.ListAdapter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.RequestType;

public abstract class EndLessListVM<T extends IPresenter, R extends IView, Q> extends ListRespVM<T, R, Q> implements ILoadMore {

    private int pageNum = Constants.PAGE_NUM_START;
    private boolean hasMore = true;

    public EndLessListVM(T presenter, R view, LinearLayoutManager layoutManager, ListAdapter<Q> adapter) {
        super(presenter, view, layoutManager, adapter);
    }

    @Override
    public void initiallyReq(@RequestType int requestType) {
        super.initiallyReq(requestType);
        exePageRequest(Constants.PAGE_NUM_START);
    }

    @Override
    protected void handleResponse(ListRespEntity<Q> data) {
        hasMore = data != null && data.isHasMore();
        switch (getRequestType()) {
            case RequestType.CONTENT_LOADING:
            case RequestType.SWIP_REFRESH:
            case RequestType.SILENT:
                super.handleResponse(data);
                pageNum = Constants.PAGE_NUM_START;
                break;
            case RequestType.LOAD_MORE:
                bindLoadMoreResp(data);
                pageNum++;
                break;
        }

    }

    @Override
    protected void handleErrorState(@RequestType int requestType) {
        super.handleErrorState(requestType);
        if (requestType == RequestType.LOAD_MORE) {
            // TODO: 11/11/2016 Hide load more view
        }
    }

    @Override
    protected final void exeRequest() {
    }

    @Override
    public final void onLoadMore() {
//        if (getState() == PageState.LOAD_MORE || !hasMore)
//            return;
//        setRequestType(RequestType.LOAD_MORE);
//        exePageRequest(pageNum + 1);
        // TODO: 11/11/2016 Show load more view

    }

    protected abstract void exePageRequest(int pageNum);

    private void bindLoadMoreResp(ListRespEntity<Q> data) {
        getAdapter().addItems(data.getList());
    }

}
