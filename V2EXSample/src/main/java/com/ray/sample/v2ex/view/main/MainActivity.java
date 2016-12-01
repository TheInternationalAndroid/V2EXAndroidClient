/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 23:28:02 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 23:28:02 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.view.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;

import com.ray.mvvm.lib.view.base.page.BaseDIActivity;
import com.ray.mvvm.lib.view.base.view.ILifeCycle;
import com.ray.sample.v2ex.R;
import com.ray.sample.v2ex.view.main.contract.DaggerMainContract_Comp;
import com.ray.sample.v2ex.view.main.contract.MainContract;
import com.ray.sample.v2ex.view.main.vm.MainVM;
import com.ray.sample.v2ex.view.main.vm.module.MainVMModule;

import javax.inject.Inject;

import rx.subjects.Subject;

public class MainActivity extends BaseDIActivity implements MainContract.View {

    @Inject MainVM viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLayout(R.layout.activity_main, viewModel, false);
        viewModel.requestPermission(this);
//        modeCheck();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_day:
                switchDayNight(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.action_night:
                switchDayNight(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchDayNight(@AppCompatDelegate.NightMode int mode) {
        getDelegate().setLocalNightMode(mode);
        recreate();
    }

    private void modeCheck() {
        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                getSupportActionBar().setTitle(getString(R.string.app_name_format, "Day"));
            case Configuration.UI_MODE_NIGHT_YES:
                getSupportActionBar().setTitle(getString(R.string.app_name_format, "Night"));
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                getSupportActionBar().setTitle(getString(R.string.app_name_format, "Sysyem"));
        }
    }

    @Override
    protected ILifeCycle getPageLifeCycle() {
        return viewModel.presenter();
    }

    @Override
    public void buildComp() {
        DaggerMainContract_Comp
                .builder()
                .activityComp(getActivityComp())
                .mainVMModule(new MainVMModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showPermissionDialog(Subject<Boolean, Boolean> subject) {
        new AlertDialog.Builder(this)
                .setTitle("Permission request")
                .setMessage("We need camera & storage permissions.")
                .setNegativeButton("Allow", (dialogInterface, i) -> subject.onNext(true))
                .setPositiveButton("Cancel", (dialogInterface, i) -> finish())
                .show();
    }
}
