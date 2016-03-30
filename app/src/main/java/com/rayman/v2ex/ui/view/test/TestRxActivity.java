package com.rayman.v2ex.ui.view.test;

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestRxBinding;
import com.rayman.v2ex.presenter.ILifeCycle;
import com.rayman.v2ex.ui.view.base.BaseDIActivity;
import com.rayman.v2ex.ui.view.test.comp.DaggerTestRxComp;
import com.rayman.v2ex.viewmodel.test.TestRxVM;

import javax.inject.Inject;

public class TestRxActivity extends BaseDIActivity {

    @Inject
    TestRxVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTestRxBinding binding = bindLayout(R.layout.activity_test_rx);
        binding.setViewModel(viewModel);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerTestRxComp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }
}
