/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 6:36 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: WorkerCallback.
 * Author: Lena; Last Modified: 3/2/16 6:36 PM.
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

package com.rayman.v2ex.model.worker;

import com.rayman.v2ex.http.callback.LSubscriber;
import com.rayman.v2ex.http.callback.ReqCallback;
import com.rayman.v2ex.http.event.ErrorEvent;

/**
 * Created by Android Studio.
 * ProjectName: shenbian_android_cloud_speaker
 * Author:  Lena.t.Yan
 * Date: 2/22/16
 * Time: 18:28
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
public class WorkerCallback<T> extends LSubscriber<T> {

    private BaseWorker worker;
    private ReqCallback<T> callback;

    public WorkerCallback(BaseWorker worker, ReqCallback<T> callback) {
        this.worker = worker;
        this.callback = callback;
    }

    @Override public void onStart() {
        super.onStart();
        if (callback != null && worker.isAlive())
            callback.onReqStart();
    }

    @Override public void onSuccess(T respEntity) {
        if (callback != null && worker.isAlive())
            callback.onNetResp(respEntity);
    }

    @Override public void onError(ErrorEvent errorEvent) {
        if (callback != null && worker.isAlive())
            callback.onError(errorEvent);
    }
}