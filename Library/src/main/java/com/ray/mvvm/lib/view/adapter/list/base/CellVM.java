/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:47:37 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:47:37 +0800.
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

package com.ray.mvvm.lib.view.adapter.list.base;

import android.databinding.BaseObservable;
import android.view.View;

import com.ray.mvvm.lib.view.adapter.OnItemClick;

public class CellVM<T> extends BaseObservable {

    protected T entity;
    protected int position;
    private OnItemClick<T> itemClick;

    public CellVM() {
    }

    public CellVM(T entity, OnItemClick<T> itemClick) {
        this.entity = entity;
        this.itemClick = itemClick;
    }

    public CellVM(T entity) {
        this.entity = entity;
    }

    public CellVM(T entity, int position) {
        this.entity = entity;
        this.position = position;
    }

    public CellVM(T entity, int position, OnItemClick<T> itemClick) {
        this.entity = entity;
        this.position = position;
        this.itemClick = itemClick;
    }

    public void setItemClick(OnItemClick<T> itemClick) {
        this.itemClick = itemClick;
    }

    public T getEntity() {
        return entity;
    }

    public OnItemClick<T> getItemClick() {
        return itemClick;
    }

    public void onContentClicked(View view) {
        if (itemClick != null)
            itemClick.onItemClick(position, view, entity);
    }

    public int getPosition() {
        return position;
    }

}
