/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 2:55 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: OkHttpModule.
 * Author: Lena; Last Modified: 1/19/16 2:55 PM.
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

package com.rayman.v2ex.di.modules.base;

import android.text.TextUtils;

import com.rayman.v2ex.cache.IFileControl;
import com.rayman.v2ex.di.scope.PerApplication;
import com.rayman.v2ex.utils.LogUtil;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 14:55
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
@Module
public class OkHttpModule {

    private static final long CONNECT_TIMEOUT = 30_000;
    private static final long WRITE_TIMEOUT = 30_000;
    private static final long READ_TIMEOUT = 30_000;
    private static final int REQUEST_CACHE_SIZE = 10 * 1024 * 1024;
    private static final int POOLING_MAX_CONNECTIONS = 5;
    private static final int REQUEST_KEEP_ALIVE_DEFAULT = 30000;

    @Provides
    @PerApplication
    OkHttpClient provideOkHttp(IFileControl fileCache) {
        final OkHttpClient httpClient = new OkHttpClient();
//        final Logger sLogger = Logger.getLogger(LogUtil.sLogTag);
//        sLogger.setLevel(Level.FINE);
        httpClient.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.setReadTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        httpClient.setCache(new Cache(new File(fileCache.requestCacheFloderPath()), REQUEST_CACHE_SIZE));
        httpClient.setConnectionPool(new ConnectionPool(POOLING_MAX_CONNECTIONS, REQUEST_KEEP_ALIVE_DEFAULT));
        httpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                long t1 = System.nanoTime();
                Request request = chain.request();
                Request.Builder builder = request.newBuilder();

                if (request.body() != null && TextUtils.isEmpty(request.headers().get("Content-Type"))) {
                    builder.header("Content-Type", "gzip").method(request.method(), request.body()).build();
                }
                request = builder
                        .addHeader("User-Agent", System.getProperty("http.agent"))
                        .build();
                LogUtil.i(String.format("Sending request %s on Connecttion: %s %n Headers: %s ",
                        request.httpUrl(),
                        chain.connection(),
                        request.headers()));

                Response response = chain.proceed(request);

                long t2 = System.nanoTime();
                LogUtil.i(
                        String.format(
                                "Received response for " +
                                        "%s in %.1fms %n" +
                                        "Response Status Code: %s %n" +
                                        "Response Headers: %s %n" +
                                        "Response CacheControl:%s %n" +
                                        "Response Body :%s %n" +
                                        "Is From Cached Response: %s %n",
                                request.httpUrl(),
                                (t2 - t1) / 1e6d,
                                response.code(),
                                response.headers(),
                                response.cacheControl(),
                                response,
                                response.cacheResponse() == response.networkResponse()));
                return response;
            }
        });
        return httpClient;

    }
}
