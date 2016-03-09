/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 4:03 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: ActivityComp.
 * Author: Lena; Last Modified: 1/19/16 4:03 PM.
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

package com.rayman.v2ex.di.component.view.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.rayman.v2ex.anotations.ContextType;
import com.rayman.v2ex.di.component.app.AppComp;
import com.rayman.v2ex.di.modules.ActivityModule;
import com.rayman.v2ex.di.scope.PerBaseActivity;
import com.rayman.v2ex.view.base.BaseActivity;

import javax.inject.Named;

import dagger.Component;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 22:08
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
@PerBaseActivity
@Component(modules = {ActivityModule.class}, dependencies = AppComp.class)
public interface ActivityComp extends AppComp {

    void inject(BaseActivity baseActivity);

    @Named(ContextType.ACTIVITY) Context activityContext();

    Activity activity();

    FragmentManager fragmentManager();

}
