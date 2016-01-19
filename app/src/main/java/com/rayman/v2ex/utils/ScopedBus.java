/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:44 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: ScopedBus.
 * Author: Lena; Last Modified: 1/19/16 3:44 PM.
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

package com.rayman.v2ex.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:44
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
public class ScopedBus {

    private final Bus bus;
    private final Set<Object> objects = new HashSet<>();
    private boolean active = true;

    private static ScopedBus sScopedBus;

    public static ScopedBus instance() {
        if (sScopedBus == null)
            sScopedBus = new ScopedBus(new Bus(ThreadEnforcer.MAIN));
        return sScopedBus;
    }

    public ScopedBus(Bus bus) {
        this.bus = bus;
    }

    public void register(Object obj) {
        if (!objects.contains(obj)) {
            objects.add(obj);
            if (active)
                bus.register(obj);
        }
    }

    public void unregister(Object obj) {
        if (objects.contains(obj)) {
            objects.remove(obj);
            if (active)
                bus.unregister(obj);
        }
    }

    public void post(Object event) {
        bus.post(event);
    }

    void paused() {
        active = false;
        for (Object obj : objects) {
            bus.unregister(obj);
        }
    }

    void resumed() {
        active = true;
        for (Object obj : objects) {
            bus.register(obj);
        }
    }
}
