package com.ray.sample.v2ex.view.templates.vm;

import android.support.v7.widget.LinearLayoutManager;

import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.view.adapter.list.base.ListAdapter;
import com.ray.mvvm.lib.viewmodel.EndLessListRespVM;
import com.ray.sample.v2ex.view.templates.contract.RayListNoToolbarContract;

public class RayListNoToolbarVM extends EndLessListRespVM<RayListNoToolbarContract.Presenter, RayListNoToolbarContract.View, TestEntity> {

    public RayListNoToolbarVM(RayListNoToolbarContract.Presenter presenter, RayListNoToolbarContract.View view, LinearLayoutManager layoutManager, ListAdapter<TestEntity> adapter) {
        super(presenter, view, layoutManager, adapter);
    }

    @Override
    protected void exePageRequest(int pageNum) {
    }

}