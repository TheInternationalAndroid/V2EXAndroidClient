package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.ListCellTestRxBinding;
import com.rayman.v2ex.model.model.test.TestRxEntity;
import com.rayman.v2ex.ui.adapter.list.base.BaseListAdapter;
import com.rayman.v2ex.ui.adapter.list.base.CellVM;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 10:36 AM
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
public class TestRxListAdapter extends BaseListAdapter<TestRxEntity> {

    public TestRxListAdapter(List<TestRxEntity> list) {
        super(list);
    }

    @Override
    protected ViewDataBinding buildBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return ListCellTestRxBinding.inflate(layoutInflater, parent, false);
    }

    @Override
    protected CellVM<TestRxEntity> getViewModel(int position) {
        TestRxEntity entity = getItem(position);
        return new CellVM<>(entity, position, entity == null ? null : entity.getOnItemClick());
    }
}
