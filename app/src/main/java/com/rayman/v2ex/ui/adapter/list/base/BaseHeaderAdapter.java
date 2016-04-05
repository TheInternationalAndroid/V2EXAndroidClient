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
