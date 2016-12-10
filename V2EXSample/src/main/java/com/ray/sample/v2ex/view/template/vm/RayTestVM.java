package com.ray.sample.v2ex.view.template.vm;

import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.template.contract.RayTestContract;

public class RayTestVM extends BaseVM<RayTestContract.Presenter, RayTestContract.View> {

    public RayTestVM(RayTestContract.Presenter presenter, RayTestContract.View view) {
        super(presenter, view);
    }

}
