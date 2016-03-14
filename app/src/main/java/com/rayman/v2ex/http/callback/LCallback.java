/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:42 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: LCallback.
 * Author: Lena; Last Modified: 1/19/16 3:42 PM.
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

import com.rayman.v2ex.eventbus.RxBus;
import com.rayman.v2ex.http.ErrorType;
import com.rayman.v2ex.http.event.ErrorEvent;

import java.io.IOException;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:42
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

public abstract class LCallback<P> implements Callback<P> {

    @Override
    public void onResponse(Response<P> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            if (response.body() == null) {
                onFailure(new Exception());
            } else {
                onSuccess(response.body());
            }
        } else {
            ErrorEvent errorEvent;
            errorEvent = new ErrorEvent(response.code(), response.message());
            RxBus.instance().post(errorEvent);
            onError(errorEvent);
        }
    }

    abstract public void onSuccess(P respEntity);

    abstract public void onError(ErrorEvent errorEvent);

    @Override
    public void onFailure(Throwable t) {
        ErrorEvent errorEvent;
        if (t instanceof IOException) {
            errorEvent = new ErrorEvent(ErrorType.ERROR_NETWORK, "网络异常");
        } else {
            errorEvent = new ErrorEvent(ErrorType.ERROR_OTHER, "未知异常");
        }
        RxBus.instance().post(errorEvent);
        onError(errorEvent);
    }
}
