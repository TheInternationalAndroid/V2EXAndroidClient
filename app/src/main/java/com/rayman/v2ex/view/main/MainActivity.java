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

package com.rayman.v2ex.view.main;

import android.os.Bundle;
import android.view.MenuItem;

import com.rayman.v2ex.R;
import com.rayman.v2ex.databinding.ActivityMainBinding;
import com.rayman.v2ex.di.component.view.main.DaggerMainComp;
import com.rayman.v2ex.eventbus.RxBus;
import com.rayman.v2ex.eventbus.event.BaseEvent;
import com.rayman.v2ex.presenter.IPage;
import com.rayman.v2ex.view.base.BaseDIActivity;
import com.rayman.v2ex.vm.main.MainActivityVM;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends BaseDIActivity {

    @Inject
    MainActivityVM viewModel;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = bindLayout(R.layout.activity_main, false);
        binding.setViewModel(viewModel);
        subscription = RxBus.getInstance()
                .asObservable(BaseEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<BaseEvent>() {
                    @Override
                    public void call(BaseEvent baseEvent) {
                        showToast(baseEvent.getMessage());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public void onInject() {
        DaggerMainComp.builder()
                .activityComp(getActivityComp())
                .build()
                .inject(this);
    }

    @Override
    protected IPage getPageCallback() {
        return viewModel;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.syncState(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            viewModel.homeClicked();
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        viewModel.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}
