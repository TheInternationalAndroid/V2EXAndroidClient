package com.ray.sample.v2ex.view.template.vm;

import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.template.contract.RayBasicContract;

public class RayBasicVM extends BaseVM<RayBasicContract.Presenter, RayBasicContract.View> {

    public RayBasicVM(RayBasicContract.Presenter presenter, RayBasicContract.View view) {
        super(presenter, view);
    }

}
