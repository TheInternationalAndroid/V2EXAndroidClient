package com.rayman.v2ex.ui.view.test.rx;

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestRxBinding;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
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
        DaggerTestRxContract_Comp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }
}
