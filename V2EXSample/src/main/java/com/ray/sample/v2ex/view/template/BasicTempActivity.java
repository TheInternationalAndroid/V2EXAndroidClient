package com.ray.sample.v2ex.view.template;

import android.os.Bundle;

import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.template.contract.BasicTempContract;
import com.ray.sample.v2ex.view.template.contract.DaggerBasicTempContract_Comp;
import com.ray.sample.v2ex.view.template.vm.BasicTempVM;
import com.ray.sample.v2ex.view.template.vm.module.BasicTempVMModule;

import javax.inject.Inject;

public class BasicTempActivity extends BaseDIActivity implements BasicTempContract.View {

    @Inject BasicTempVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_basic_temp, viewModel);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerBasicTempContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .basicTempVMModule(new BasicTempVMModule(this))
                .build()
                .inject(this);
    }
}
