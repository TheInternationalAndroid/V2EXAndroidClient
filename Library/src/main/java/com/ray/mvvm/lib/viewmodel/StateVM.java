/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Wed, 16 Nov 2016 10:41:11 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Wed, 16 Nov 2016 10:41:11 +0800.
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

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.ray.mvvm.lib.BR;
import com.ray.mvvm.lib.widget.anotations.ListViewItemType;
import com.ray.mvvm.lib.widget.anotations.PageState;
import com.ray.mvvm.lib.widget.utils.StringUtil;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 16/11/2016
 * Time: 10:41 AM
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
public abstract class StateVM extends BaseObservable {

    private int emptyIconRes;
    private int emptyMsgRes = com.ray.mvvm.lib.R.string.state_empty_msg;
    private String errorString;
    private int state = PageState.LOADING;
    private int listItemType = ListViewItemType.NO_MORE;
    private boolean isNetworkError = false;

    public StateVM() {
    }

    public StateVM(@PageState int state) {
        this.state = state;
    }

    public abstract void onRetryClicked(View view);

    @PageState
    public int getState() {
        return state;
    }

    public void setState(@PageState int state) {
        if (this.state == state)
            return;
        this.state = state;
        notifyPropertyChanged(BR.errorVisibility);
        notifyPropertyChanged(BR.emptyVisibility);
        notifyPropertyChanged(BR.loadingVisibility);
        notifyPropertyChanged(BR.contentVisibility);
    }

    public void setListItemType(@ListViewItemType int listItemType) {
        if (this.listItemType == listItemType)
            return;
        this.listItemType = listItemType;
        notifyPropertyChanged(BR.loadingVisibility);
        notifyPropertyChanged(BR.noMoreVisibility);
    }

    @ListViewItemType
    public int getListItemType() {
        return listItemType;
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
        notifyPropertyChanged(emptyMsgRes);
        notifyPropertyChanged(emptyIconRes);
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

    @Bindable
    public boolean isNetworkError() {
        return isNetworkError;
    }

    public void setNetworkError(boolean networkError) {
        isNetworkError = networkError;
        notifyPropertyChanged(BR.networkError);
    }

    @Bindable
    public int getEmptyVisibility() {
        return state == PageState.EMPTY ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getErrorVisibility() {
        return state == PageState.ERROR ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getLoadingVisibility() {
        return state == PageState.LOADING ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getLoadMoreVisibility() {
        return listItemType == ListViewItemType.LOAD_MORE ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getNoMoreVisibility() {
        return listItemType == ListViewItemType.NO_MORE ? View.VISIBLE : View.GONE;
    }

    @Bindable
    public int getContentVisibility() {
        return state == PageState.CONTENT ? View.VISIBLE : View.GONE;
    }
}
