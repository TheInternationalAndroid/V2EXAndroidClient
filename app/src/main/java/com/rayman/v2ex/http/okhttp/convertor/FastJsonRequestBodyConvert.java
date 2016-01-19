/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 2:52 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: FastJsonRequestBodyConvert.
 * Author: Lena; Last Modified: 1/19/16 2:52 PM.
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

package com.rayman.v2ex.http.okhttp.convertor;

import com.alibaba.fastjson.JSON;
import com.rayman.v2ex.utils.LogUtil;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.IOException;
import java.nio.charset.Charset;

import retrofit.Converter;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 14:50
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

public class FastJsonRequestBodyConvert<T> implements Converter<T, RequestBody> {

    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        String postBody = JSON.toJSONString(value);
        LogUtil.i("Http: Post Json Body " + postBody);
        return RequestBody.create(MEDIA_TYPE, postBody.getBytes(UTF_8));
    }
}