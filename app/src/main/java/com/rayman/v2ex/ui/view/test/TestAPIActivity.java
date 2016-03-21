package com.rayman.v2ex.ui.view.test;

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestApiBinding;
import com.rayman.v2ex.presenter.ILifeCycle;
import com.rayman.v2ex.ui.view.base.BaseDIActivity;
import com.rayman.v2ex.viewmodel.test.TestApiVM;

import javax.inject.Inject;

public class TestApiActivity extends BaseDIActivity {

    @Inject
    TestApiVM viewModel;

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
        DaggerTestApiComp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }

}
