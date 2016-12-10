package com.ray.sample.v2ex.view.template;

import com.ray.mvvm.lib.view.base.page.BaseDIFragment;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.template.contract.DaggerRayTestContract_Comp;
import com.ray.sample.v2ex.view.template.contract.RayTestContract;
import com.ray.sample.v2ex.view.template.vm.RayTestVM;
import com.ray.sample.v2ex.view.template.vm.module.RayTestVMModule;

import javax.inject.Inject;

public class RayTestFragment extends BaseDIFragment implements RayTestContract.View {

    @Inject RayTestVM viewModel;

    @Override
    protected int onCreateView() {
        return R.layout.fragment_ray_test;
    }

    @Override
    public void buildComp() {
        DaggerRayTestContract_Comp
                .builder()
                .fragmentComp(fragmentComp())
                .rayTestVMModule(new RayTestVMModule(this))
                .build()
                .inject(this);

    }
}
