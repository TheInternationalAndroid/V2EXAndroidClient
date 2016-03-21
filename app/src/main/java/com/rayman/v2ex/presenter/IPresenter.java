/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 9:53 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: IPresenter.
 * Author: Lena; Last Modified: 1/18/16 9:53 PM.
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

import com.rayman.v2ex.widget.eventbus.event.BaseEvent;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 21:43
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

public interface IPresenter extends ILifeCycle {

    void subcribe(Subscription subscription);

    <T extends BaseEvent> void subcribeEvent(Class<T> aClass, Action1<T> action1);

    void bindLifecycleCallback(ILifeCycle... lifecycles);
}
