/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 22:57:52 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 22:57:52 +0800.
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

package com.ray.mvvm.lib.di.modules;

import android.content.Context;

import com.ray.mvvm.lib.BuildConfig;
import com.ray.mvvm.lib.db.ITopicDBManager;
import com.ray.mvvm.lib.db.TopicDBManager;
import com.ray.mvvm.lib.di.scope.PerApplication;
import com.ray.mvvm.lib.model.http.adapter.RespEntityAdapter;
import com.ray.mvvm.lib.widget.anotations.ContextType;
import com.squareup.moshi.Moshi;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

@Module
public class ComponentModule {

    @Provides
    @PerApplication
    Moshi provideMoshi() {
        return new Moshi.Builder()
                .add(new RespEntityAdapter())
                .build();
    }

    @Provides
    @PerApplication
    Realm provideRealm(@Named(ContextType.APPLICATION) Context context) {
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .migration((realm, oldVersion, newVersion) ->
                        Timber.i("migration  Old version %d -> New version %d", oldVersion, newVersion))
                .schemaVersion(BuildConfig.VERSION_CODE)
                .deleteRealmIfMigrationNeeded()
                .name("Library.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        return Realm.getDefaultInstance();
    }

    @Provides
    @PerApplication
    RxPermissions provideRxPermission(@Named(ContextType.APPLICATION) Context context) {
        return RxPermissions.getInstance(context);
    }

    @Provides
    @PerApplication
    ITopicDBManager provideTopicDB(Realm realm) {
        return new TopicDBManager(realm);
    }

}
