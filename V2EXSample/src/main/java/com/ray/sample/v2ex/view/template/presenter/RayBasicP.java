package com.ray.sample.v2ex.view.template.presenter;

import com.ray.mvvm.lib.presenter.BasePresenter;
import com.ray.sample.v2ex.view.template.contract.RayBasicContract;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

public class RayBasicP extends BasePresenter implements RayBasicContract.Presenter {

    @Inject
    RayBasicP(RefWatcher refWatcher) {
        super(refWatcher);
    }

}