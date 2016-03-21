/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 4:06 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: MainActivityVMModule.
 * Author: Lena; Last Modified: 1/19/16 4:06 PM.
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

package com.rayman.v2ex.viewmodel.main;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.rayman.v2ex.R;
import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.presenter.main.MainActivityP;
import com.rayman.v2ex.ui.adapter.pager.MainPagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 23:33
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
@Module
public class MainActivityVMModule {

    @Provides
    @PerActivity
    MainActivityVM provideMainActivityVM(MainActivityP presenter, FragmentManager fragmentManager, Activity activity) {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(fragmentManager, activity.getResources().getStringArray(R.array.main_tab_title));
        MaterialMenuIconToolbar menuIconToolbar = new MaterialMenuIconToolbar(activity, Color.WHITE, MaterialMenuDrawable.Stroke.THIN) {
            @Override
            public int getToolbarViewId() {
                return R.id.toolbar;
            }
        };
        menuIconToolbar.setNeverDrawTouch(false);
        return new MainActivityVM(presenter, mainPagerAdapter, menuIconToolbar);
    }

}