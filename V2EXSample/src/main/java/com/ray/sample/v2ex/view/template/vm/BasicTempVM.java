package com.ray.sample.v2ex.view.template.vm;

import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.template.contract.BasicTempContract;

public class BasicTempVM extends BaseVM<BasicTempContract.Presenter, BasicTempContract.View> {

    public BasicTempVM(BasicTempContract.Presenter presenter, BasicTempContract.View view) {
        super(presenter, view);
    }

}
