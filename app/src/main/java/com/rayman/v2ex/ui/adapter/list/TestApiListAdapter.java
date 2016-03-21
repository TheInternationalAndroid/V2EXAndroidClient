package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.ListCellTestApiBinding;
import com.rayman.v2ex.model.model.TestApiEntity;
import com.rayman.v2ex.ui.adapter.OnItemClick;
import com.rayman.v2ex.viewmodel.test.TestApiCellVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 5:26 PM
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
public class TestApiListAdapter extends BaseListAdapter<TestApiEntity, TestApiCellVM> {

    private OnItemClick<TestApiEntity> onItemClick;

    public TestApiListAdapter(OnItemClick<TestApiEntity> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @Override
    protected ViewDataBinding buildBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return ListCellTestApiBinding.inflate(layoutInflater, parent, false);
    }

    @Override
    protected TestApiCellVM getViewModel(int position) {
        return new TestApiCellVM(getItem(position), position, onItemClick);
    }

}
