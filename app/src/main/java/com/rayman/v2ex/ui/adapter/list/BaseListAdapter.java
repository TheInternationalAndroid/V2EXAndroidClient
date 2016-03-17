/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/4/16 4:41 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseListAdapter.
 * Author: Lena; Last Modified: 3/4/16 4:41 PM.
 * This file is originally created by Lena.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.ui.adapter.list.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 3/4/16
 * Time: 11:28
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
public abstract class BaseListAdapter<T, P> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> list;
    protected LongSparseArray<T> wrapMap = new LongSparseArray<>();

    public void setList(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
        wrapMap.clear();
        if (getItemCount() > 0) {
            for (T t : this.list) {
                wrapMap.put(getIndex(t), t);
            }
        }
    }

    public List<T> getList() {
        return list;
    }

    public final T getItem(int position) {
        return list == null ? null : list.get(position);
    }

    @Override public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(buildBinding(LayoutInflater.from(parent.getContext()), parent, viewType));
    }

    @Override public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override public final void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindData(getViewModel(position));
    }

    protected abstract ViewDataBinding buildBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    protected abstract P getViewModel(int position);

    public void addItem(int position, T t) {
        if (t == null)
            return;
        if (list == null) {
            list = new ArrayList<>();
            wrapMap.clear();
        }
        list.add(position, t);
        wrapMap.put(getIndex(t), t);
        notifyItemInserted(position);
    }

    public void addItem(T t) {
        addItem(0, t);
    }

    public boolean updateItem(T t) {
        if (t == null) return false;
        long key = getIndex(t);
        T localItem = wrapMap.get(key);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        wrapMap.put(key, t);
        list.set(position, t);
        notifyItemChanged(position);
        return true;
    }

    public void removeItemByIndex(long key) {
        T localItem = wrapMap.get(key);
        if (localItem == null) return;
        final int position = list.indexOf(localItem);
        if (position == -1) return;
        wrapMap.remove(key);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItemByPosition(int position) {
        T t = list.get(position);
        if (t == null) return;
        wrapMap.remove(getIndex(t));
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(T t) {
        if (t == null) return;
        long key = getIndex(t);
        T localItem = wrapMap.get(key);
        if (localItem == null) return;
        final int position = list.indexOf(localItem);
        if (position == -1) return;
        wrapMap.remove(key);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void clearList() {
        if (getItemCount() > 0) {
            list.clear();
            notifyDataSetChanged();
            wrapMap.clear();
        }
    }

    public void resetList() {
        if (getItemCount() > 0) {
            list = new ArrayList<>();
            notifyDataSetChanged();
            wrapMap.clear();
        }
    }

    public long getIndex(T t) {
        return -1;
    }

}
