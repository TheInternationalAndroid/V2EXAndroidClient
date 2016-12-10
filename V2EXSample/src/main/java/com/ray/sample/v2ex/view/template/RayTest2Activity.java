package com.ray.sample.v2ex.view.template;

import android.os.Bundle;

import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.template.contract.RayTest2Contract;
import com.ray.sample.v2ex.view.template.contract.DaggerRayTest2Contract_Comp;
import com.ray.sample.v2ex.view.template.vm.RayTest2VM;
import com.ray.sample.v2ex.view.template.vm.module.RayTest2VMModule;

import javax.inject.Inject;


public class RayTest2Activity extends BaseDIActivity implements RayTest2Contract.View {

    @Inject RayTest2VM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_ray_test2, viewModel);
    }

    @Override
    public void buildComp() {
        DaggerRayTest2Contract_Comp
                .builder()
                .activityComp(getActivityComp())
                .rayTest2VMModule(new RayTest2VMModule(this))
                .build()
                .inject(this);
    }
}
