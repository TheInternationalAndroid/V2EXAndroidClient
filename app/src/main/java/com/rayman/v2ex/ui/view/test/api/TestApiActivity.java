package com.rayman.v2ex.ui.view.test.api;

import android.os.Bundle;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityTestApiBinding;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
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
        DaggerTestApiContract_Comp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }

}
