/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:43 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: ErrorType.
 * Author: Lena; Last Modified: 1/19/16 3:43 PM.
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

package com.rayman.v2ex.model.http;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:43
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
public interface ErrorType {

    int ERROR_INVALID_USER = 401;
    int ERROR_AUTH = 403;
    int ERROR_SERVER_AUTH = 1001;
    int ERROR_SERVER = 1003;
    int ERROR_NETWORK = 1004;
    int ERROR_ALREADY_REGIST = 5001;
    int ERROR_UN_REGIST_YET = 5002;
    int ERROR_LIMITED_USER = 5003;

    int ERROR_OTHER = 9999;
}
