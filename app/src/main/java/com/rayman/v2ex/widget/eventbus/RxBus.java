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

package com.rayman.v2ex.widget.eventbus;

import com.rayman.v2ex.widget.eventbus.event.BaseEvent;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/11/16
 * Time: 3:01 PM
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
public class RxBus {

    private static volatile RxBus instance = new RxBus();

    private Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

    public static RxBus instance() {
        RxBus rxBus = instance;
        if (rxBus == null) {
            synchronized (RxBus.class) {
                rxBus = instance;
                if (rxBus == null) {
                    rxBus = instance = new RxBus();
                }
            }
        }
        return rxBus;
    }

    public void post(Object event) {
        bus.onNext(event);
    }

    public <T extends BaseEvent> Observable<T> asObservable(final Class<T> aClass) {
        return bus.asObservable()
                .filter(event ->
                        aClass.isInstance(event)
                )
                .cast(aClass);
    }

    public Observable<BaseEvent> asObservable() {
        return bus.asObservable().cast(BaseEvent.class);
    }


    public boolean hasObservers() {
        return bus.hasObservers();
    }

}
