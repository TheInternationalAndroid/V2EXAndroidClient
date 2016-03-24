/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 2:52 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: FastJsonResponseBodyConverter.
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

package com.rayman.v2ex.model.http.okhttp.convertor;

import com.alibaba.fastjson.JSON;
import com.rayman.v2ex.model.http.okhttp.RequestContentType;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import timber.log.Timber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 14:52
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

public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            String jsonString = new String(value.bytes(), RequestContentType.CHARSET);
            Timber.i("Request: Parse Response Json : %s %n", jsonString);
            return JSON.parseObject(jsonString, type);
        } catch (Exception e) {
            Timber.e("Request: Parse Json Error : %s", e.getMessage());
        }
        return null;
    }
}