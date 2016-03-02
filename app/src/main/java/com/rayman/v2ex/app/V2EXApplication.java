/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 9:57 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: V2EXApplication.
 * Author: Lena; Last Modified: 1/18/16 9:57 PM.
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

package com.rayman.v2ex.app;

import android.app.Application;

import com.rayman.v2ex.di.IInject;
import com.rayman.v2ex.di.component.app.AppComp;
import com.rayman.v2ex.di.component.app.DaggerAppComp;
import com.rayman.v2ex.di.modules.AppModule;
import com.rayman.v2ex.http.event.ErrorEvent;
import com.rayman.v2ex.utils.LogUtil;
import com.rayman.v2ex.utils.ScopedBus;
import com.rayman.v2ex.utils.StringUtil;
import com.rayman.v2ex.utils.ToastUtil;
import com.squareup.otto.Subscribe;

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
public class V2EXApplication extends Application implements IInject {

    private AppComp appComp;

    @Override public void onCreate() {
        super.onCreate();

        LogUtil.setDebug(true);

        onInject();

        ScopedBus.instance().register(this);
    }

    @Override public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ScopedBus.instance().unregister(this);
    }

    @Override public void onLowMemory() {
        super.onLowMemory();
        ScopedBus.instance().unregister(this);
    }

    @Override public void onInject() {
        appComp = DaggerAppComp
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComp.inject(this);
    }

    public AppComp appComp() {
        return appComp;
    }

    @Subscribe public void handleRequestError(ErrorEvent errorEvent) {
        if (!StringUtil.isEmpty(errorEvent.getMessage()))
            ToastUtil.show(this, errorEvent.getMessage());
    }

}
