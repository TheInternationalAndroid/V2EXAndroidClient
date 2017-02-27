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

package com.ray.sample.v2ex.view.mock.presenter;

import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.model.ListRespEntity;
import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.presenter.CommonPresenter;
import com.ray.sample.v2ex.view.mock.contract.WrapperListSampleContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class WrapperListSampleP extends CommonPresenter implements WrapperListSampleContract.Presenter {

    private int index = 0;

    @Inject
    WrapperListSampleP() {
    }

    @Override
    public void requestListData(int page, ExObserver<ListRespEntity<TestEntity>> observer) {
        mockResp(() -> {
            List<TestEntity> testEntities = new ArrayList<>();
            ListRespEntity<TestEntity> respEntity = new ListRespEntity<>();
            if (page == 1)
                index = 0;
            if (page <= 2) {
                for (int i = 0; i < 20; i++) {
                    TestEntity testEntity = new TestEntity("Title" + index, "Description");
                    testEntities.add(testEntity);
                    index += 1;
                }
                respEntity.setHasMore(true);
            }
            respEntity.setTotalCount(100);
            respEntity.setList(testEntities);
            return respEntity;
        })
                .compose(applyAsyncRequest(observer))
                .subscribe(observer);
    }
}
