/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:04 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: FileCacheModule.
 * Author: Lena; Last Modified: 1/19/16 3:04 PM.
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

import android.content.Context;

import com.rayman.v2ex.anotations.ContextType;
import com.rayman.v2ex.di.scope.PerApplication;
import com.rayman.v2ex.model.cache.FileControl;
import com.rayman.v2ex.model.cache.IFileControl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User:  Lena.t.Yan
 * Date: 12/10/15
 * Time: 12:57
 */
@Module
public class FileCacheModule {

    @Provides
    @PerApplication
    IFileControl provideFileCache(@Named(ContextType.APPLICATION) Context context) {
        return new FileControl(context);
    }

}
