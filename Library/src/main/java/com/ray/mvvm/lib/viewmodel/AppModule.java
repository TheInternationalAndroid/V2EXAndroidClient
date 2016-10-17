/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:56:12 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:56:12 +0800.
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

package com.ray.mvvm.lib.viewmodel;

import android.app.Application;
import android.content.Context;

import com.ray.mvvm.lib.di.modules.SPModule;
import com.ray.mvvm.lib.di.scope.PerApplication;
import com.ray.mvvm.lib.model.cache.FileModule;
import com.ray.mvvm.lib.model.http.RetrofitModule;
import com.ray.mvvm.lib.model.http.okhttp.OkHttpModule;
import com.ray.mvvm.lib.widget.anotations.ContextType;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        SPModule.class,
        OkHttpModule.class,
        RetrofitModule.class,
        ComponentModule.class,
        FileModule.class
})
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Named(ContextType.APPLICATION)
    Context provideAppContext() {
        return application;
    }

    @Provides
    @PerApplication
    Application provideApp() {
        return application;
    }

    @Provides
    @PerApplication
    RefWatcher provideRefWatcher() {
        return LeakCanary.install(application);
    }

}
