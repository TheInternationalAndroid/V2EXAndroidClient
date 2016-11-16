/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 12 Nov 2016 21:29:47 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 12 Nov 2016 21:29:47 +0800.
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

package com.ray.sample.v2ex.view.mock.presenter;

import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.presenter.BasePresenter;
import com.ray.sample.v2ex.view.mock.contract.ListSampleContract;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListSampleP extends BasePresenter implements ListSampleContract.Presenter {

    @Inject
    ListSampleP(RefWatcher refWatcher) {
        super(refWatcher);
    }

    @Override
    public void requestData(ExObserver<List<TestEntity>> observer) {
        List<TestEntity> testEntities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestEntity testEntity = new TestEntity("Title" + i, "Description");
            testEntities.add(testEntity);
        }
        subscribeCommonReq(mockCommonRespObservable(testEntities), observer);
    }
}