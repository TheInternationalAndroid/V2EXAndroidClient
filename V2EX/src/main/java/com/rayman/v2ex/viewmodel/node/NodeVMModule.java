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

package com.rayman.v2ex.viewmodel.node;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.di.modules.LayoutManagerModule;
import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.node.NodeContract;
import com.rayman.v2ex.ui.view.node.NodeP;
import com.rayman.v2ex.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/7/16
 * Time: 10:22 AM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
@Module(includes = {LayoutManagerModule.class})
public class NodeVMModule {

    private NodeContract.View view;

    public NodeVMModule(NodeContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    NodeVM provideNodeVM(NodeP presenter, @Named(ListType.VERTICAL) RecyclerView.LayoutManager layoutManager) {
        return new NodeVM(presenter, view, layoutManager);
    }

}
