/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 11:31 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: MainVM.
 * Author: Lena; Last Modified: 1/18/16 11:31 PM.
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

package com.rayman.v2ex.vm.main;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.extras.toolbar.MaterialMenuIconToolbar;
import com.rayman.v2ex.adapter.pager.MainPagerAdapter;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 23:31
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
public class MainActivityVM extends BaseObservable implements DrawerLayout.DrawerListener {

    private MainPagerAdapter adapter;
    private MaterialMenuIconToolbar menuIconToolbar;
    private boolean isDrawerOpen = false;

    public MainActivityVM(MainPagerAdapter adapter, MaterialMenuIconToolbar menuIconToolbar) {
        this.adapter = adapter;
        this.menuIconToolbar = menuIconToolbar;
    }


    @Override public void onDrawerSlide(View drawerView, float slideOffset) {
        menuIconToolbar.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_ARROW, isDrawerOpen ? 2 - slideOffset : slideOffset);
    }

    @Override public void onDrawerOpened(View drawerView) {
        isDrawerOpen = true;
    }

    @Override public void onDrawerClosed(View drawerView) {
        isDrawerOpen = false;
    }

    @Override public void onDrawerStateChanged(int newState) {

    }

    public void onSaveInstanceState(Bundle bundle) {
        menuIconToolbar.onSaveInstanceState(bundle);
    }

    public void syncState(Bundle bundle) {
        menuIconToolbar.syncState(bundle);
    }

    public void homeClicked() {
        setDrawerOpen(!isDrawerOpen);
    }

    @Bindable public boolean isDrawerOpen() {
        return isDrawerOpen;
    }

    public void setDrawerOpen(boolean drawerOpen) {
        isDrawerOpen = drawerOpen;
        notifyPropertyChanged(BR.drawerOpen);
    }

    public MainPagerAdapter getAdapter() {
        return adapter;
    }

}
