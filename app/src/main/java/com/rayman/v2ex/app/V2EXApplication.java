/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 22:22:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 22:22:35 +0800.
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

package com.rayman.v2ex.app;

import android.support.multidex.MultiDexApplication;

import com.rayman.v2ex.BuildConfig;
import com.rayman.v2ex.di.IBuildComp;
import com.rayman.v2ex.model.http.event.ErrorEvent;
import com.rayman.v2ex.viewmodel.AppModule;
import com.rayman.v2ex.widget.eventbus.RxBus;
import com.rayman.v2ex.widget.utils.StringUtil;
import com.rayman.v2ex.widget.utils.ToastUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 21:57
 * \ ___________________
 * \| Happy New Year!  |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class V2EXApplication extends MultiDexApplication implements IBuildComp {

    private AppComp appComp;
    private Subscription subscription;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
//            AndroidDevMetrics.initWith(this);
            Timber.plant(new Timber.DebugTree());
        }

        buildComp();

        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = RxBus.instance()
                .asObservable(ErrorEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleEvent);
    }

    public void handleEvent(ErrorEvent errorEvent) {
        if (errorEvent != null && !StringUtil.isEmpty(errorEvent.getMessage())) {
            ToastUtil.show(V2EXApplication.this, errorEvent.getMessage());
        }
    }

    @Override
    public void buildComp() {
        appComp = DaggerAppComp
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComp.inject(this);
    }

    public AppComp appComp() {
        return appComp;
    }

}
