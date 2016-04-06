package com.rayman.v2ex.ui.view.test.api;

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestApiBinding;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
import com.rayman.v2ex.viewmodel.test.TestApiServiceVM;
import com.rayman.v2ex.viewmodel.test.TestApiVMModule;

import javax.inject.Inject;

public class TestApiActivity extends BaseDIActivity implements TestApiContract.View {

    @Inject
    TestApiServiceVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestApiBinding binding = bindLayout(R.layout.activity_test_api);
        binding.setViewModel(viewModel);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTestApiContract_Comp.builder()
                .activityComp(getActivityComp())
                .testApiVMModule(new TestApiVMModule(this))
                .build()
                .inject(this);
    }

}
