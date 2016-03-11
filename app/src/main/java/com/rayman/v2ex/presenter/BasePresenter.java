/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 11:10 AM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BasePresenter.
 * Author: Lena; Last Modified: 3/2/16 11:10 AM.
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

package com.rayman.v2ex.presenter;

import com.rayman.v2ex.eventbus.event.BaseEvent;
import com.rayman.v2ex.worker.BaseWorker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Subscription;
import rx.functions.Action1;

public class BasePresenter implements IPresenter {

    private List<IPage> lifecycleCallbacks = new ArrayList<>();

    private BaseWorker observerWorker = new BaseWorker();

    public BasePresenter() {
        bindLifecycleCallback(observerWorker);
    }

    @Override
    public void onViewAttach() {
        for (IPage iPageLifecycle : lifecycleCallbacks) {
            iPageLifecycle.onViewAttach();
        }
    }

    @Override
    public void onViewDetach() {
        for (IPage iPageLifecycle : lifecycleCallbacks) {
            iPageLifecycle.onViewDetach();
        }
        lifecycleCallbacks.clear();
    }

    public void subcribe(Subscription subscription) {
        observerWorker.subscribe(subscription);
    }

    public <T extends BaseEvent> void subcribeEvent(Class<T> aClass, Action1<T> action1) {
        observerWorker.subscribeEvent(aClass, action1);
    }

    protected void bindLifecycleCallback(IPage... lifecycles) {
        lifecycleCallbacks.addAll(Arrays.asList(lifecycles));
    }
}
