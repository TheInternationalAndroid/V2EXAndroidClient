package com.ray.sample.v2ex.view.template.vm;

import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.template.contract.RayTest2Contract;

public class RayTest2VM extends BaseVM<RayTest2Contract.Presenter, RayTest2Contract.View> {

    public RayTest2VM(RayTest2Contract.Presenter presenter, RayTest2Contract.View view) {
        super(presenter, view);
    }

}
