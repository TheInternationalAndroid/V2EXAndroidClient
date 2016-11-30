package com.ray.sample.v2ex.view.template;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ray.mvvm.lib.view.base.page.BaseDIFragment;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.sample.v2ex.view.template.contract.DaggerRayBasicContract_Comp;
import com.ray.sample.v2ex.view.template.vm.RayBasicVM;
import com.ray.sample.v2ex.view.template.vm.module.RayBasicVMModule;
import com.ray.sample.v2ex.view.template.contract.RayBasicContract;
import com.ray.sample.v2ex.databinding.FragmentRayBasicBinding;
import com.ray.sample.v2ex.R;

import javax.inject.Inject;

public class RayBasicFragment extends BaseDIFragment implements RayBasicContract.View {

    @Inject RayBasicVM viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRayBasicBinding binding = FragmentRayBasicBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
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
