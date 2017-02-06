/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.view.adapter.list.base.StateListAdapter;
import com.ray.sample.v2ex.databinding.ListCellTestEntityBinding;
import com.ray.sample.v2ex.view.common.mock.TestEntityCellView;
import com.ray.sample.v2ex.view.mock.vm.TestEntityVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 12/11/2016
 * Time: 9:40 PM
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
public class TestListAdapter extends StateListAdapter<TestEntity> {

    private final TestEntityCellView view;

    public TestListAdapter(TestEntityCellView view) {
        this.view = view;
    }

    @Override
    protected ViewDataBinding createBinding(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return ListCellTestEntityBinding.inflate(layoutInflater, parent, false);
    }

    @Override
    protected Object createViewModel(RecyclerView.ViewHolder holder, int position) {
        return new TestEntityVM(getItem(position), holder, this.view);
    }

    @Override
    public long getIndex(TestEntity testEntity) {
        return testEntity.getId();
    }
}
