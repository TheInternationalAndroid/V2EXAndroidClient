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
import com.ray.mvvm.lib.view.base.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.PageState;
import com.ray.mvvm.lib.widget.anotations.RequestType;
import com.ray.mvvm.lib.widget.utils.StringUtil;

import java.io.IOException;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 18:10
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
public abstract class BaseStateVM<T extends IPresenter, R extends IView, Q> extends BaseVM<T, R> implements ExObserver<Q> {

    private int requestType = RequestType.CONTENT_LOADING;
    private int state;
    private int emptyIconRes;
    private int emptyMsgRes = com.ray.mvvm.lib.R.string.state_empty_msg;
    private String errorString;
    private boolean isNetworkError = false;

    public BaseStateVM(T presenter, R view) {
        super(presenter, view);
    }

    @Bindable
    public int getState() {
        return state;
    }

    public void setState(@PageState int state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    @Bindable
    public int getEmptyMsgRes() {
        return emptyMsgRes;
    }

    @Bindable
    public int getEmptyIconRes() {
        return emptyIconRes;
    }

    public void setEmptyInfo(int emptyIcon, int emptyMsgRes) {
        this.emptyIconRes = emptyIcon;
        this.emptyMsgRes = emptyMsgRes;
        notifyPropertyChanged(BR.emptyMsgRes);
        notifyPropertyChanged(BR.emptyIconRes);
    }

    @Bindable
    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        if (StringUtil.isEmpty(errorString)) return;
        this.errorString = errorString;
        notifyPropertyChanged(BR.errorString);
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
                setState(PageState.CONTENT);
                break;
            case RequestType.LOAD_MORE:
                setState(PageState.LOAD_MORE);
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

    protected void setRequestType(int requestType) {
        this.requestType = requestType;
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
//        setErrorString(view.findString(com.istuary.ironhide.lib.R.string.state_error_network_msg));
        setErrorString(errorString);
        throwable.printStackTrace();
    }

    @Bindable
    public boolean isNetworkError() {
        return isNetworkError;
    }

    public void setNetworkError(boolean networkError) {
        isNetworkError = networkError;
        notifyPropertyChanged(BR.networkError);
    }

    @Override
    public void onNext(Q data) {
        handleCompleteState(requestType, data);
        handleResponse(data);
    }

    @Override
    public void onCompleted() {
    }

    private void requestData(@RequestType int requestType) {
        this.requestType = requestType;
        exeRequest();
    }

    protected void handleResponse(Q data) {
        bindResp(data);
    }

    protected abstract void exeRequest();

    protected abstract void bindResp(Q data);

    public void initiallyReq(@RequestType int requestType) {
        requestData(requestType);
    }

    public void onRetryClicked(View view) {
        initiallyReq(RequestType.CONTENT_LOADING);
    }

    protected boolean isRespNull(Q data) {
        return data == null;
    }

}
