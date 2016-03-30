package com.rayman.v2ex.ui.adapter.list.base;

import android.databinding.BaseObservable;
import android.view.View;

import com.rayman.v2ex.ui.adapter.OnItemClick;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 10:43 AM
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
public class CellVM<T> extends BaseObservable {

    protected T entity;
    private int position;
    private OnItemClick<T> itemClick;

    public CellVM() {
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
            itemClick.onItemClick(position, entity);
    }

    public int getPosition() {
        return position;
    }
}
