/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on 1/20/16 12:18 PM
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: 1/20/16 12:18 PM.
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

package com.rayman.v2ex.viewmodel;

import android.databinding.Bindable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.rayman.v2ex.BR;
import com.rayman.v2ex.widget.anotations.PageState;
import com.rayman.v2ex.widget.anotations.RequestType;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 11:58 PM
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
public abstract class BaseSwipStateVM<T, R> extends BaseStateVM<T, R> implements SwipeRefreshLayout.OnRefreshListener {

    private int[] colors = {com.rayman.v2ex.R.color.colorPrimaryDark};
    private boolean isRefreshing;

    public BaseSwipStateVM(T presenter, R view) {
        super(presenter, view);
    }

    @Bindable
    public boolean isRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
        notifyPropertyChanged(BR.refreshing);
    }

    public int[] getColors() {
        return colors;
    }

    protected void controlStartState(@RequestType int requestType) {
        switch (requestType) {
            case RequestType.CONTENT_LOADING:
                setState(PageState.LOADING);
                break;
            case RequestType.SWIP_REFRESH:
                if (!isRefreshing)
                    setRefreshing(true);
                break;
            case RequestType.SILENT:
                break;
        }
    }

    protected void controlSuccessState(@RequestType int requestType) {
        if (requestType == RequestType.SWIP_REFRESH) {
            if (isRefreshing())
                setRefreshing(false);
        }
    }

    protected void controlErrorState(
            @RequestType
            int requestType, String erroString) {
        switch (requestType) {
            case RequestType.CONTENT_LOADING:
                showError(PageState.ERROR, erroString);
                break;
            case RequestType.SWIP_REFRESH:
                if (!isRefreshing)
                    setRefreshing(false);
                break;
            case RequestType.SILENT:
                break;
        }
    }

}
