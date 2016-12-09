package com.ray.sample.v2ex.view.template;

import com.ray.mvvm.lib.view.base.page.BaseDIFragment;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.template.contract.DaggerRayBasicContract_Comp;
import com.ray.sample.v2ex.view.template.contract.RayBasicContract;
import com.ray.sample.v2ex.view.template.vm.RayBasicVM;
import com.ray.sample.v2ex.view.template.vm.module.RayBasicVMModule;

import javax.inject.Inject;

public class RayBasicFragment extends BaseDIFragment implements RayBasicContract.View {

    @Inject RayBasicVM viewModel;

    @Override
    protected int onCreateView() {
        return R.layout.fragment_ray_basic;
    }

    @Override
    public void buildComp() {
        DaggerRayBasicContract_Comp
                .builder()
                .fragmentComp(fragmentComp())
                .rayBasicVMModule(new RayBasicVMModule(this))
                .build()
                .inject(this);
    }
}
