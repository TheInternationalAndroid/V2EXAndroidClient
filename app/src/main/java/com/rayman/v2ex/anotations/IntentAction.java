/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 10:21 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: IntentAction.
 * Author: Lena; Last Modified: 1/18/16 10:21 PM.
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

package com.rayman.v2ex.anotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.rayman.v2ex.anotations.IntentAction.ACTION_ADD;
import static com.rayman.v2ex.anotations.IntentAction.ACTION_DELETE;
import static com.rayman.v2ex.anotations.IntentAction.ACTION_UPDATE;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 22:21
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
@Documented
@Retention(RetentionPolicy.SOURCE)
@IntDef({ACTION_ADD, ACTION_DELETE, ACTION_UPDATE})
public @interface IntentAction {

    String PARA_ACTION_KEY = "PARA_ACTION_KEY";
    int ACTION_ADD = 1;
    int ACTION_DELETE = 2;
    int ACTION_UPDATE = 3;

}

