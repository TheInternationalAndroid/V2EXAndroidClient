/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 12:06 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseDIActivity.
 * Author: Lena; Last Modified: 3/2/16 12:06 PM.
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

package com.rayman.v2ex.view.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;

import com.rayman.v2ex.R;
import com.rayman.v2ex.app.V2EXApplication;
import com.rayman.v2ex.di.IInject;
import com.rayman.v2ex.di.component.app.AppComp;
import com.rayman.v2ex.di.component.view.base.ActivityComp;
import com.rayman.v2ex.di.component.view.base.DaggerActivityComp;
import com.rayman.v2ex.di.modules.ActivityModule;
import com.rayman.v2ex.presenter.IPage;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 3/2/16
 * Time: 12:06
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
public abstract class BaseDIActivity extends BaseActivity implements IInject {

    private ActivityComp activityComp;

    @Override protected void onDestroy() {
        IPage pager = getPageCallback();
        if (pager != null)
            pager.onViewDetach();
        activityComp.refWatchwer().watch(this);
        super.onDestroy();
    }

    protected AppComp getAppComp() {
        return ((V2EXApplication) getApplication()).appComp();
    }

    protected ActivityComp getActivityComp() {
        if (activityComp == null)
            activityComp = DaggerActivityComp
                    .builder()
                    .appComp(getAppComp())
                    .activityModule(new ActivityModule(this))
                    .build();
        return activityComp;
    }

    public <P extends ViewDataBinding> P bindLayout(int layoutRes) {
        return bindLayout(layoutRes, true);
    }

    public <P extends ViewDataBinding> P bindLayout(int layoutRes, boolean homeAsUp) {
        P binding = DataBindingUtil.setContentView(this, layoutRes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setSubtitle("");
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
        }
        onInject();

        IPage page = getPageCallback();
        if (page != null)
            page.onViewAttach();

        return binding;
    }

    protected abstract IPage getPageCallback();

}
