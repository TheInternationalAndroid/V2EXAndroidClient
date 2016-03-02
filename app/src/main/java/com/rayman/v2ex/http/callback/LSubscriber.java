/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 5:44 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: LSubscriber.
 * Author: Lena; Last Modified: 3/2/16 5:44 PM.
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

package com.rayman.v2ex.http.callback;

import com.rayman.v2ex.http.ErrorType;
import com.rayman.v2ex.http.event.ErrorEvent;
import com.rayman.v2ex.utils.ScopedBus;

import java.io.IOException;

import retrofit.Response;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 3/2/16
 * Time: 17:44
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
public abstract class LSubscriber<T> extends Subscriber<Response<T>> {

    @Override public void onCompleted() {
    }

    @Override public void onError(Throwable e) {
        ErrorEvent errorEvent;
        if (e instanceof IOException) {
            errorEvent = new ErrorEvent(ErrorType.ERROR_NETWORK, "网络异常");
        } else {
            errorEvent = new ErrorEvent(ErrorType.ERROR_OTHER, "未知异常");
        }
        ScopedBus.instance().post(errorEvent);
        onError(errorEvent);
    }

    @Override public void onNext(Response<T> response) {
        if (response.isSuccess()) {
            if (response.body() == null) {
                onError(new Exception("Response body is empty."));
            } else {
                onSuccess(response.body());
            }
        } else {
            ErrorEvent errorEvent;
            errorEvent = new ErrorEvent(response.code(), response.message());
            ScopedBus.instance().post(errorEvent);
            onError(errorEvent);
        }
    }

    abstract public void onSuccess(T respEntity);

    abstract public void onError(ErrorEvent errorEvent);

}
