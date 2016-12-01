/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 14 Oct 2016 16:39:18 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 14 Oct 2016 16:39:18 +0800.
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

package com.ray.sample.v2ex.view.main.vm;

import android.content.Context;
import android.view.View;

import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.main.contract.MainContract;
import com.ray.sample.v2ex.view.mock.MockSamplesActivity;
import com.ray.sample.v2ex.view.v2ex.TopicListActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.subjects.PublishSubject;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainVM extends BaseVM<MainContract.Presenter, MainContract.View> {

    public MainVM(MainContract.Presenter presenter, MainContract.View view) {
        super(presenter, view);
    }

    public void onV2exClicked(View view) {
        this.view.intent(TopicListActivity.class);
    }

    public void onMockDataClicked(View view) {
        this.view.intent(MockSamplesActivity.class);
    }

    public void requestPermission(Context context) {
        PublishSubject<Boolean> subject = PublishSubject.create();
        presenter.subscribe(
                subject
                        .compose(RxPermissions
                                .getInstance(context)
                                .ensure(WRITE_EXTERNAL_STORAGE,
                                        ACCESS_COARSE_LOCATION,
                                        ACCESS_FINE_LOCATION,
                                        CAMERA))
                        .subscribe(granted -> {
                            if (granted) {
                                subject.onCompleted();
                            } else {
                                view.showPermissionDialog(subject);
                            }
                        })
        );
        subject.onNext(true);
    }
}
