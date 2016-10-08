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

package com.rayman.v2ex.di.modules;

import com.rayman.v2ex.di.scope.PerApplication;
import com.rayman.v2ex.model.http.Host;
import com.rayman.v2ex.model.http.okhttp.convertor.FastJsonConvertFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Android Studio.
 * ProjectName: shenbian_android_cloud_speaker
 * Author:  Lena.t.Yan
 * Date: 12/21/15
 * Time: 16:14
 * \ ___________________
 * \| Merry Christmas!  |
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
public class RetrofitModule {

    @Provides
    @PerApplication
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .baseUrl(Host.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(FastJsonConvertFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(JacksonConverterFactory.create())
//                .addConverterFactory(MoshiConverterFactory.create())
//                .addConverterFactory(ProtoConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .addConverterFactory(SimpleXmlConverterFactory.create())
//                .addConverterFactory(WireConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addCallAdapterFactory(Java8CallAdapterFactory.create())
//                .addCallAdapterFactory(GuavaCallAdapterFactory.create())
                .build();
    }

}
