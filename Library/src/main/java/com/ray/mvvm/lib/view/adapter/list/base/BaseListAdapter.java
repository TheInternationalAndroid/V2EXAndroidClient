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

import android.databinding.ViewDataBinding;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ray.mvvm.lib.app.Constants;
import com.ray.mvvm.lib.databinding.ListFooterEmptyBinding;
import com.ray.mvvm.lib.databinding.ListFooterLoadMoreBinding;
import com.ray.mvvm.lib.databinding.ListFooterNoMoreBinding;
import com.ray.mvvm.lib.view.adapter.OnItemClick;
import com.ray.mvvm.lib.view.adapter.list.viewholder.BaseViewHolder;
import com.ray.mvvm.lib.widget.anotations.FooterState;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int NO_INDEX = -99;
    private List<T> list;
    private LongSparseArray<T> wrapMap = new LongSparseArray<>();
    protected OnItemClick<T> itemClick;
    private static final int VIEW_TYPE_NO_MORE = 52123;
    private static final int VIEW_TYPE_LOAD_MORE = 52124;
    private static final int VIEW_TYPE_EMPTY = 52125;
    private int footerState = FooterState.NONE;


    public BaseListAdapter() {
    }

    public BaseListAdapter(OnItemClick<T> itemClick) {
        this.itemClick = itemClick;
    }

    public BaseListAdapter(List<T> list) {
        this.list = list;
    }

    public BaseListAdapter(List<T> list, OnItemClick<T> itemClick) {
        this.list = list;
        this.itemClick = itemClick;
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NO_MORE:
                return new BaseViewHolder(ListFooterNoMoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
            case VIEW_TYPE_LOAD_MORE:
                return new BaseViewHolder(ListFooterLoadMoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
            case VIEW_TYPE_EMPTY:
                return new BaseViewHolder(ListFooterEmptyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
            default:
                return new BaseViewHolder(createBinding(LayoutInflater.from(parent.getContext()), parent, viewType));
        }

    }

    @Override
    public int getItemCount() {
        return (list == null ? 0 : list.size()) + getHeaderCount() + getFooterCount();
    }

    public int getDataCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_NO_MORE:
                break;
            case VIEW_TYPE_LOAD_MORE:
                break;
            case VIEW_TYPE_EMPTY:
                break;
            default:
                holder.bindData(createViewModel(getItemViewType(position), position));
                break;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    protected abstract ViewDataBinding createBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType);

    protected Object createViewModel(int viewType, int position) {
        return new CellVM<>(getItem(position), position, itemClick);
    }

    public void setList(List<T> list) {
        this.list = list;
        wrapMap.clear();
        if (getItemCount() > 0) {
            for (T t : this.list) {
                final long index = getIndex(t);
                if (index == NO_INDEX)
                    break;
                wrapMap.put(index, t);
            }
        }
        notifyDataSetChanged();
    }

    public void setListWithoutHeader(List<T> list) {
        int oldSize = 0;
        if (this.list != null) {
            oldSize = this.list.size();
        }
        this.list = list;
        wrapMap.clear();
        if (getItemCount() > 0) {
            for (T t : this.list) {
                final long index = getIndex(t);
                if (index == NO_INDEX)
                    break;
                wrapMap.put(index, t);
            }
        }
        notifyItemRangeRemoved(getHeaderCount(), oldSize);
        notifyItemRangeInserted(getHeaderCount(), list.size());
    }

    public List<T> getList() {
        return list;
    }

    public void setItemClick(OnItemClick<T> itemClick) {
        this.itemClick = itemClick;
    }

    public final T getItem(int position) {
        final int headerCount = getHeaderCount();
        return list == null ? null : list.get(position - headerCount);
    }

    public T getItemByIndex(long id) {
        return wrapMap.get(id);
    }

    public void addItem(T t) {
        addItem(0, t);
    }

    public void addItem(int position, T t) {
        if (t == null)
            return;
        if (list == null) {
            list = new ArrayList<>();
            wrapMap.clear();
        }
        list.add(position, t);
        final long index = getIndex(t);
        if (index != NO_INDEX)
            wrapMap.put(index, t);
        notifyItemInserted(position);
    }

    public void addItems(List<T> insertList) {
        if (insertList == null || insertList.size() == 0)
            return;
        if (list == null) {
            list = new ArrayList<>();
            wrapMap.clear();
        }
        int oldCount = getItemCount();
        int insertCount = insertList.size();
        list.addAll(insertList);
        for (T t : insertList) {
            long index = getIndex(t);
            if (index == NO_INDEX)
                break;
            wrapMap.put(index, t);
        }
        notifyItemRangeInserted(oldCount, insertCount);
//        notifyDataSetChanged();
    }

    public void updateItem(int position, T item) {
        if (position != Constants.NO_POSITION && item != null) {
            T origin = getItem(position);
            if (origin != null && getIndex(origin) == getIndex(item)) {
                final int headerCount = getHeaderCount();
                list.set(position - headerCount, item);
                notifyItemChanged(position, null);
            }
        }
    }

    public boolean notifyItem(T t) {
        if (t == null) return false;
        final long key = getIndex(t);
        if (key == NO_INDEX) return false;
        T localItem = wrapMap.get(key);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        final int headerCount = getHeaderCount();
        notifyItemChanged(position + headerCount);
        return true;
    }

    public boolean updateItem(T t) {
        if (t == null) return false;
        final long key = getIndex(t);
        if (key == NO_INDEX) return false;
        T localItem = wrapMap.get(key);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        final int headerCount = getHeaderCount();
        wrapMap.put(key, t);
        list.set(position, t);
        notifyItemChanged(position + headerCount);
        return true;
    }

    public boolean updateItemByIndex(long id) {
        T localItem = wrapMap.get(id);
        if (localItem == null) return false;
        final int position = list.indexOf(localItem);
        if (position == -1) return false;
        final int headerCount = getHeaderCount();
        notifyItemChanged(position + headerCount);
        return true;
    }

    public void removeItemByIndex(long key) {
        T localItem = wrapMap.get(id);
        if (localItem == null) return;
        final int position = list.indexOf(localItem);
        if (position == -1) return;
        final int headerCount = getHeaderCount();
        wrapMap.remove(key);
        list.remove(position);
        notifyItemRemoved(position + headerCount);
    }

    public void removeItemByPosition(int position) {
        T t = list.get(position);
        if (t == null) return;
        final long index = getIndex(t);
        if (index == NO_INDEX) return;
        wrapMap.remove(index);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeItem(T t) {
        removeItem(t, false);
    }

    public void removeItem(T t, boolean notifyAll) {
        if (t == null) return;
        long key = getIndex(t);
        if (key == NO_INDEX) return;
        T localItem = wrapMap.get(key);
        if (localItem == null) return;
        int position = list.indexOf(localItem);
        if (position == -1) return;
        final int viewPosition = position + getHeaderCount();
        wrapMap.remove(key);
        list.remove(position);
        if (notifyAll || list.size() == 0) {
            notifyDataSetChanged();
        } else {
            notifyItemRemoved(viewPosition);
        }
    }

    public void removeItemNotifySelf(T t) {
        if (t == null) return;
        long key = getIndex(t);
        if (key == NO_INDEX) return;
        T localItem = wrapMap.get(key);
        if (localItem == null) return;
        int position = list.indexOf(localItem);
        if (position == -1) return;
        final int viewPosition = position + getHeaderCount();
        wrapMap.remove(key);
        list.remove(position);
        notifyItemChanged(viewPosition);
    }

    public int getRealPosition(T t) {
        if (t == null) return RecyclerView.NO_POSITION;
        final long key = getIndex(t);
        if (key == NO_INDEX) return RecyclerView.NO_POSITION;
        T localItem = wrapMap.get(key);
        if (localItem == null) return RecyclerView.NO_POSITION;
        return list.indexOf(localItem);
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

    public ArrayList<T> getArrayList() {
        if (list != null)
            return new ArrayList<>(list);
        return null;
    }


    public long getIndex(T t) {
        return NO_INDEX;
    }

    public int getHeaderCount() {
        return 0;
    }

    private int getFooterCount() {
        return (footerState != FooterState.NONE && getDataCount() > 0) ? 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getHeaderCount() + getDataCount()) {
            if (footerState == FooterState.NO_MORE) {
                return VIEW_TYPE_NO_MORE;
            } else if (footerState == FooterState.LOAD_MORE) {
                return VIEW_TYPE_LOAD_MORE;
            } else if (footerState == FooterState.EMPTY) {
                return VIEW_TYPE_EMPTY;
            } else {
                return super.getItemViewType(position);

            }
        } else {
            return super.getItemViewType(position);
        }
    }

    public int getFooterState() {
        return footerState;
    }

    public void setFooterState(@FooterState int state) {
        this.footerState = state;
        notifyItemChanged(getItemCount() - 1);
    }

}
