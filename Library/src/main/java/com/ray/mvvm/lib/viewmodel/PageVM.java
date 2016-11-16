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

import android.databinding.Bindable;
import android.view.View;

import com.ray.mvvm.lib.BR;
import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.http.event.ErrorEvent;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.PageState;
import com.ray.mvvm.lib.widget.anotations.RequestType;

import java.io.IOException;

public abstract class PageVM<T extends IPresenter, R extends IView, Q> extends BaseStateVM<T, R> implements ExObserver<Q>, View.OnClickListener {

    private int requestType = RequestType.CONTENT_LOADING;
    private Q entity;

    public PageVM(T presenter, R view) {
        super(presenter, view);
    }

    private void handleStartState(@RequestType int requestType) {
        switch (requestType) {
            case RequestType.CONTENT_LOADING:
                setState(PageState.LOADING);
                break;
            case RequestType.SWIP_REFRESH:
                setState(PageState.REFRESH);
                break;
            case RequestType.SILENT:
            case RequestType.LOAD_MORE:
                setState(PageState.CONTENT);
                break;
        }
    }

    private void handleCompleteState(@RequestType int requestType, Q data) {
        switch (requestType) {
            case RequestType.CONTENT_LOADING:
            case RequestType.SWIP_REFRESH:
            case RequestType.SILENT:
                setState(isRespNull(data) ? PageState.EMPTY : PageState.CONTENT);
                break;
            case RequestType.LOAD_MORE:
                setState(PageState.CONTENT);
                break;
        }
    }

    protected void handleErrorState(@RequestType int requestType) {
        switch (requestType) {
            case RequestType.CONTENT_LOADING:
                setState(PageState.ERROR);
                break;
            case RequestType.LOAD_MORE:
            case RequestType.SWIP_REFRESH:
            case RequestType.SILENT:
                setState(PageState.CONTENT);
                break;
        }
    }

    @Override
    public void onRetryClicked(View view) {
        initiallyReq(RequestType.CONTENT_LOADING);
    }

    int getRequestType() {
        return requestType;
    }

    @Override
    public void onStart() {
        handleStartState(requestType);
    }

    @Override
    public void onError(Throwable throwable) {
        handleErrorState(requestType);
        String errorString;
        if (throwable instanceof ErrorEvent) {
            errorString = throwable.getMessage();
        } else if (throwable instanceof IOException) {
            setNetworkError(true);
            errorString = view.findString(com.ray.mvvm.lib.R.string.state_error_network_msg);
        } else {
            errorString = throwable.getMessage();
        }
        view.showToast(errorString);
        setErrorString(errorString);
        throwable.printStackTrace();
    }

    @Override
    public void onNext(Q data) {
        handleResponse(data);
        handleCompleteState(requestType, data);
    }

    @Override
    public void onCompleted() {
    }

    public void setEntity(Q entity) {
        this.entity = entity;
        notifyPropertyChanged(BR.entity);
    }

    @Bindable
    public Q getEntity() {
        return entity;
    }

    protected void handleResponse(Q data) {
        bindResp(data);
    }

    protected abstract void exeRequest();

    protected void bindResp(Q data) {
        setEntity(data);
    }

    public void initiallyReq(@RequestType int requestType) {
        this.requestType = requestType;
        exeRequest();
    }

    @Override
    public void onClick(View view) {
        initiallyReq(RequestType.CONTENT_LOADING);
    }

    protected boolean isRespNull(Q data) {
        return data == null;
    }

}
