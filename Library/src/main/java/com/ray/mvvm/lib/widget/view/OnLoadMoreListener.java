/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sun, 9 Oct 2016 00:22:07 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sun, 9 Oct 2016 00:22:07 +0800.
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

package com.ray.mvvm.lib.widget.view;

import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ray.mvvm.lib.interfaces.ILoadMore;
import com.ray.mvvm.lib.interfaces.OnItemSwitch;
import com.ray.mvvm.lib.interfaces.OnScrollListener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.ray.mvvm.lib.widget.view.OnLoadMoreListener.ScrollType.SCROLL_DOWN;
import static com.ray.mvvm.lib.widget.view.OnLoadMoreListener.ScrollType.SCROLL_UP;

public class OnLoadMoreListener extends RecyclerView.OnScrollListener {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SCROLL_UP, SCROLL_DOWN})
    public @interface ScrollType {
        int SCROLL_UP = 0;
        int SCROLL_DOWN = 1;
    }

    private LinearLayoutManager layoutManager;
    private OnScrollListener onScrollListener;
    private OnItemSwitch onItemSwitch;
    private ILoadMore onLoadMore;

    private int lastPosition;

    public OnLoadMoreListener(LinearLayoutManager layoutManager, ILoadMore onLoadMore) {
        this.layoutManager = layoutManager;
        this.onLoadMore = onLoadMore;
    }

    public void setOnItemSwitch(OnItemSwitch onItemSwitch) {
        this.onItemSwitch = onItemSwitch;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (onScrollListener != null)
            onScrollListener.onScrolled(recyclerView, dx, dy);
        final int totalItemCount = layoutManager.getItemCount();
        final int pastItemCount = layoutManager.findFirstVisibleItemPosition();
        final int visibleItemCount = layoutManager.getChildCount();
        final int currentLastPosition = layoutManager.findLastVisibleItemPosition();
        if (lastPosition != currentLastPosition) {
            int scrollType = lastPosition > currentLastPosition ? SCROLL_UP : SCROLL_DOWN;
            if (onItemSwitch != null)
                onItemSwitch.onItemSwitch(lastPosition, currentLastPosition, scrollType);
            lastPosition = currentLastPosition;
        }
        if ((visibleItemCount + pastItemCount) >= totalItemCount) {
            if (onLoadMore != null) {
                onLoadMore.onLoadMore();
            }
        }
    }
}
