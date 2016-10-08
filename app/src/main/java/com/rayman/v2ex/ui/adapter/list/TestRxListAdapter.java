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

package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.ListCellTestRxBinding;
import com.rayman.v2ex.model.model.test.TestRxEntity;
import com.rayman.v2ex.ui.adapter.list.base.BaseListAdapter;
import com.rayman.v2ex.ui.adapter.list.base.CellVM;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 10:36 AM
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
public class TestRxListAdapter extends BaseListAdapter<TestRxEntity> {

    public TestRxListAdapter(List<TestRxEntity> list) {
        super(list);
    }

    @Override
    protected ViewDataBinding buildBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return ListCellTestRxBinding.inflate(layoutInflater, parent, false);
    }

    @Override
    protected CellVM<TestRxEntity> getViewModel(int position) {
        TestRxEntity entity = getItem(position);
        return new CellVM<>(entity, position, entity == null ? null : entity.getOnItemClick());
    }
}
