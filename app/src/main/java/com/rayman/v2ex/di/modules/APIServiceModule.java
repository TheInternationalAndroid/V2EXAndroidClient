/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:36 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: APIServiceModule.
 * Author: Lena; Last Modified: 1/19/16 3:36 PM.
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

package com.rayman.v2ex.di.modules;

import com.rayman.v2ex.di.scope.PerApplication;
import com.rayman.v2ex.model.http.service.NodeService;
import com.rayman.v2ex.model.http.service.TopicService;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:32
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
public class APIServiceModule {

    @Provides
    @PerApplication
    TopicService provideTopicService(Retrofit retrofit) {
        return retrofit.create(TopicService.class);
    }

    @Provides
    @PerApplication
    NodeService provideNodeService(Retrofit retrofit) {
        return retrofit.create(NodeService.class);
    }

}
