package com.ray.sample.v2ex.view.template.presenter;

import com.ray.mvvm.lib.presenter.BasePresenter;
import com.ray.sample.v2ex.view.template.contract.BasicTempContract;

import javax.inject.Inject;

public class BasicTempP extends BasePresenter implements BasicTempContract.Presenter {

    @Inject
    BasicTempP() {
    }

}
