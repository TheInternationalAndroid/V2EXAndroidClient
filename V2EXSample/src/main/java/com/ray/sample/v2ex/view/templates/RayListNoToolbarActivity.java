package com.ray.sample.v2ex.view.templates;

import android.os.Bundle;

import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.templates.contract.RayListNoToolbarContract;
import com.ray.sample.v2ex.view.templates.contract.DaggerRayListNoToolbarContract_Comp;
import com.ray.sample.v2ex.view.templates.vm.RayListNoToolbarVM;
import com.ray.sample.v2ex.view.templates.vm.module.RayListNoToolbarVMModule;

import javax.inject.Inject;


public class RayListNoToolbarActivity extends BaseDIActivity implements RayListNoToolbarContract.View {

    @Inject RayListNoToolbarVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_ray_list_no_toolbar, viewModel);
    }

    @Override
    public void buildComp() {
        DaggerRayListNoToolbarContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .rayListNoToolbarVMModule(new RayListNoToolbarVMModule(this))
                .build()
                .inject(this);
    }
}
