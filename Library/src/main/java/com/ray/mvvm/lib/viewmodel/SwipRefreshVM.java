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
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.ray.mvvm.lib.BR;
import com.ray.mvvm.lib.presenter.IPresenter;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.PageState;
import com.ray.mvvm.lib.widget.anotations.RequestType;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.PublishSubject;

public abstract class SwipRefreshVM<T extends IPresenter, R extends IView, Q> extends PageVM<T, R, Q> implements SwipeRefreshLayout.OnRefreshListener {

    private PublishSubject<Boolean> refreshSubject;
    private boolean isRefreshing;

    private int[] colors = {com.ray.mvvm.lib.R.color.SwipRefreshColor};

    public SwipRefreshVM(T presenter, R view) {
        super(presenter, view);
        refreshSubject = PublishSubject.create();
        presenter.subscribe(refreshSubject
                        .filter(refresh -> refresh != isRefreshing)
                        .concatMap(value -> Observable.just(value).delay(!value && isRefreshing ? 2 : 0, TimeUnit.SECONDS)),
                refresh -> {
                    isRefreshing = refresh;
                    notifyPropertyChanged(BR.refreshing);
                });
    }

    public int[] getColors() {
        return colors;
    }

    @Override
    public void onRefresh() {
        if (getState() == PageState.LOADING) {
            return;
        }
        initiallyReq(RequestType.SWIP_REFRESH);
    }

    @Override
    public void setState(@PageState int state) {
        super.setState(state);
        notifyPropertyChanged(BR.swipRefreshVisibility);
        setRefreshing(getState() == PageState.REFRESH);
    }

    @Bindable
    public int getSwipRefreshVisibility() {
        final int state = getState();
        return state == PageState.LOADING || state == PageState.EMPTY || state == PageState.ERROR ? View.GONE : View.VISIBLE;
    }

    @Bindable
    public boolean isRefreshing() {
        return isRefreshing;
    }

    private void setRefreshing(boolean refreshing) {
        refreshSubject.onNext(refreshing);
    }
}
