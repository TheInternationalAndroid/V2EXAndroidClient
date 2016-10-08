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

package com.rayman.v2ex.ui.adapter.list.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.rayman.v2ex.ui.adapter.list.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 5:05 PM
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
public abstract class BaseHeaderAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> list;

    public BaseHeaderAdapter() {
    }

    public BaseHeaderAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(createBinding(parent, viewType));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindData(createViewModel(position));
    }

    @Override
    public int getItemCount() {
        final int headerCount = getHeaderCount();
        return list == null ? headerCount : list.size() + headerCount;
    }

    @Override
    public abstract int getItemViewType(int position);

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        final int headerCount = getHeaderCount();
        return list == null ? null : list.get(position - headerCount);
    }

    protected abstract int getHeaderCount();

    protected abstract ViewDataBinding createBinding(ViewGroup parent, int viewType);

    protected abstract Object createViewModel(int position);

}
