/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 12:52 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseDIFragment.
 * Author: Lena; Last Modified: 3/2/16 12:52 PM.
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

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rayman.v2ex.di.IInject;
import com.rayman.v2ex.di.component.view.base.ActivityComp;
import com.rayman.v2ex.di.component.view.base.DaggerFragmentComp;
import com.rayman.v2ex.di.component.view.base.FragmentComp;
import com.rayman.v2ex.presenter.IPage;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 3/2/16
 * Time: 12:52
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
public abstract class BaseDIFragment extends BaseFragment implements IInject {

    private FragmentComp fragmentComp;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInject();
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        IPage page = getPageCallBack();
        if (page != null)
            page.onViewAttach();
    }

    @Override public void onDetach() {
        IPage page = getPageCallBack();
        if (page != null)
            page.onViewDetach();
        super.onDetach();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        fragmentComp.refWatchwer().watch(this);
    }

    protected FragmentComp getFragmentComp() {
        if (fragmentComp == null)
            fragmentComp = DaggerFragmentComp.builder()
                    .activityComp(activityComp())
                    .build();
        return fragmentComp;
    }

    private ActivityComp activityComp() {
        return ((BaseDIActivity) getActivity()).getActivityComp();
    }

    protected abstract IPage getPageCallBack();

}
