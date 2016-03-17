/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 4:06 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotFragVMModule.
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

package com.rayman.v2ex.di.modules.vm.main;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.di.modules.base.LayoutManagerModule;
import com.rayman.v2ex.di.scope.PerBindingFragment;
import com.rayman.v2ex.presenter.main.HotFragP;
import com.rayman.v2ex.ui.view.main.IHotFragView;
import com.rayman.v2ex.viewmodel.main.HotFragVM;
import com.rayman.v2ex.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 16:04
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
@Module(includes = {LayoutManagerModule.class})
public class HotFragVMModule {

    private IHotFragView hotFragView;

    public HotFragVMModule(IHotFragView hotFragView) {
        this.hotFragView = hotFragView;
    }

    @Provides
    @PerBindingFragment
    HotFragVM provideHotFragVM(HotFragP presenter, @Named(ListType.VERTICAL) RecyclerView.LayoutManager layoutManager) {
        return new HotFragVM(presenter, hotFragView, layoutManager);
    }

}
