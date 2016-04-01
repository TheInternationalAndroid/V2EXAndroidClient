/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 10:35 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: MainActivity.
 * Author: Lena; Last Modified: 1/18/16 10:35 PM.
 * This file is originally created by Lena.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rayman.v2ex.ui.view.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityMainBinding;
import com.rayman.v2ex.ui.view.base.view.ILifeCycle;
import com.rayman.v2ex.ui.view.base.page.BaseDIActivity;
import com.rayman.v2ex.ui.view.test.api.TestApiActivity;
import com.rayman.v2ex.ui.view.test.rx.TestRxActivity;
import com.rayman.v2ex.viewmodel.main.MainActivityVM;

import javax.inject.Inject;

public class MainActivity extends BaseDIActivity {

    @Inject
    MainActivityVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = bindLayout(R.layout.activity_main, false);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityComp().refWatchwer().watch(viewModel);
    }

    @Override
    public void buildComp() {
        DaggerMainContract_Comp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.syncState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                viewModel.homeClicked();
                break;
            case R.id.action_test_api:
                intent(TestApiActivity.class);
                break;
            case R.id.action_test_rx:
                intent(TestRxActivity.class);
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        viewModel.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
