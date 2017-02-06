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
import com.ray.mvvm.lib.model.model.test.TestEntity;
import com.ray.mvvm.lib.presenter.CommonPresenter;
import com.ray.sample.v2ex.view.mock.contract.PageSampleContract;

import javax.inject.Inject;

public class PageSampleP extends CommonPresenter implements PageSampleContract.Presenter {

    @Inject
    PageSampleP() {
    }

    @Override
    public void requestEntity(ExObserver<TestEntity> observer) {
        mockResp(new TestEntity("Title (from server)", "Description (from server)"))
                .compose(applyAsync(observer))
                .subscribe(observer);
    }

}
