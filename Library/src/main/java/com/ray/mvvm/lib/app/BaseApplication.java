/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:54:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:54:35 +0800.
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

package com.ray.mvvm.lib.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ray.mvvm.lib.di.IBuildComp;
import com.ray.mvvm.lib.model.http.event.ErrorEvent;
import com.ray.mvvm.lib.model.model.RespEntity;
import com.ray.mvvm.lib.viewmodel.AppModule;
import com.ray.mvvm.lib.widget.eventbus.RxBus;
import com.ray.mvvm.lib.widget.utils.DeviceUtil;
import com.ray.mvvm.lib.widget.utils.StringUtil;
import com.ray.mvvm.lib.widget.utils.ToastUtil;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Android Studio.
 * ProjectName: IndustrialControlCircle
 * Author:  Rayman.t.Yan
 * Date: 4/18/16
 * Time: 10:42
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

public class BaseApplication extends Application implements IBuildComp {

    private AppComp appComp;
    private Subscription subscription;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildComp();
        subscribeEvent();
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

    protected void init(String channel) {
        DeviceUtil.init(this);
    }

    private void subscribeEvent() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        subscription = RxBus.instance()
                .asObservable(ErrorEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleEvent);
    }

    public void handleEvent(ErrorEvent errorEvent) {
        if (errorEvent != null) {
            if (!StringUtil.isEmpty(errorEvent.getMessage()))
                ToastUtil.show(BaseApplication.this, errorEvent.getMessage());
            if (errorEvent.getCode() == RespEntity.AUTH_ERROR) {
            }
        }
    }

}
