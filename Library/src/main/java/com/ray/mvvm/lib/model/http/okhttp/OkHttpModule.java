/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:38:42 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:38:42 +0800.
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

package com.ray.mvvm.lib.model.http.okhttp;

import android.text.TextUtils;

import com.ray.mvvm.lib.BuildConfig;
import com.ray.mvvm.lib.di.scope.PerApplication;
import com.ray.mvvm.lib.model.cache.IFileControl;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Rayman
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

    private static final long CONNECT_TIMEOUT = 30;
    private static final long WRITE_TIMEOUT = 30;
    private static final long READ_TIMEOUT = 30;
    private static final int REQUEST_CACHE_SIZE = 10 * 1024 * 1024;
    private static final int POOLING_MAX_CONNECTIONS = 5;
    private static final int REQUEST_KEEP_ALIVE_DEFAULT = 30;

    private static final String HEADER_KEY_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_KEY_USER_AGENT = "User-Agent";

    @Provides
    @PerApplication
    OkHttpClient provideOkHttp(IFileControl fileCache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpInterceptorUtil.okhttpInterceptor(builder)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(new File(fileCache.requestCacheFloderPath()), REQUEST_CACHE_SIZE))
                .connectionPool(new ConnectionPool(POOLING_MAX_CONNECTIONS, REQUEST_KEEP_ALIVE_DEFAULT, TimeUnit.SECONDS))
                .interceptors()
                .add(chain -> {
                            long t1 = System.nanoTime();
                            Request request = chain.request();
                            Request.Builder requestBuilder = request.newBuilder();
                            if (request.body() != null && TextUtils.isEmpty(request.headers().get(HEADER_KEY_CONTENT_TYPE))) {
                                requestBuilder.header(HEADER_KEY_CONTENT_TYPE, "gzip").method(request.method(), request.body()).build();
                            }

                            request = requestBuilder
                                    .addHeader(HEADER_KEY_USER_AGENT, System.getProperty("http.agent"))
                                    .build();

                            Response response = chain.proceed(request);

                            long t2 = System.nanoTime();
                            Timber.i("Sending request %s on Connecttion: %s %n Headers: %s " +
                                            "\n\nReceived response for %s in %.1fms %n Response Status Code: %s %n Response Headers: %s %nResponse CacheControl:%s %n Response Body :%s %n Is From Cached Response: %s %n",
                                    request.url(),
                                    chain.connection(),
                                    request.headers(),
                                    request.url(),
                                    (t2 - t1) / 1e6d,
                                    response.code(),
                                    response.headers(),
                                    response.cacheControl(),
                                    response.body(),
                                    response.cacheResponse() == response.networkResponse()
                            );
                            return response;
                        }
                );
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);

        builder.addInterceptor(logging);
        return builder.build();

    }
}
