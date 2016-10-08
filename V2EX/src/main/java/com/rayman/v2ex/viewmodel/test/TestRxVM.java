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

package com.rayman.v2ex.viewmodel.test;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.model.model.test.TestRxEntity;
import com.rayman.v2ex.ui.adapter.list.TestRxListAdapter;
import com.rayman.v2ex.ui.view.test.rx.TestRxContract;
import com.rayman.v2ex.viewmodel.BaseVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 10:25 AM
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
public class TestRxVM extends BaseVM<TestRxContract.Presenter, TestRxContract.View> {

    private final RecyclerView.LayoutManager layoutManager;
    private final TestRxListAdapter adapter;

    public TestRxVM(TestRxContract.Presenter presenter, TestRxContract.View view, RecyclerView.LayoutManager layoutManager) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new TestRxListAdapter(getTestList());
    }

    private List<TestRxEntity> getTestList() {
        List<TestRxEntity> list = new ArrayList<>();
        list.add(new TestRxEntity("TestThread", (position, entity) -> presenter.testThread()));
        list.add(new TestRxEntity("TestEmpty", (position, entity) -> presenter.testEmpty()));
        list.add(new TestRxEntity("TestNever", (position, entity) -> presenter.testNever()));
        list.add(new TestRxEntity("TestFrom", (position, entity) -> presenter.testFrom()));
        list.add(new TestRxEntity("TestFromFuture", (position, entity) -> presenter.testFromFuture()));
        list.add(new TestRxEntity("TestInterval", (position, entity) -> presenter.testInterval()));
        list.add(new TestRxEntity("TestJust", (position, entity) -> presenter.testJust()));
        list.add(new TestRxEntity("TestRange", (position, entity) -> presenter.testRange()));
        list.add(new TestRxEntity("TestRepeat", (position, entity) -> presenter.testRepeat()));
        list.add(new TestRxEntity("TestStartWith", (position, entity) -> presenter.testStartWith()));
        list.add(new TestRxEntity("TestTimer", (position, entity) -> presenter.testTimer()));
        return list;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public TestRxListAdapter getAdapter() {
        return adapter;
    }

}
