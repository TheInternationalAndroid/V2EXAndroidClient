package com.ray.sample.v2ex.view.template.presenter;

import com.ray.mvvm.lib.presenter.BasePresenter;
import com.ray.sample.v2ex.view.template.contract.RayTestContract;

import javax.inject.Inject;

public class RayTestP extends BasePresenter implements RayTestContract.Presenter {

    @Inject
    RayTestP() {
    }

}
