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

package com.rayman.v2ex.ui.adapter.pager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.common.base.Preconditions;
import com.rayman.v2ex.ui.view.main.hot.HotFragment;
import com.rayman.v2ex.ui.view.main.latest.LatestFragment;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 12:05
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
public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_LATEST = 0;
    private static final int TAB_HOT = 1;
    private static final int TAB_COUNT = 2;

    private String[] titles;

    public MainPagerAdapter(@NonNull FragmentManager fm, @NonNull String[] titles) {
        super(fm);
        Preconditions.checkNotNull(titles, "Fragment title array must not be null");
        if (titles.length != TAB_COUNT)
            throw new IllegalArgumentException("Titles size not match.");
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
            case TAB_LATEST:
                return new LatestFragment();
            case TAB_HOT:
                return new HotFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
