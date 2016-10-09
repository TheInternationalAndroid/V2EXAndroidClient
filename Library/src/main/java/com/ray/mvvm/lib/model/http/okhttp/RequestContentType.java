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


import okhttp3.MediaType;

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
public interface RequestContentType {
    String CHARSET = "utf-8";
    String CONTENT_TYPE_JSON = "application/json; charset=" + CHARSET;
    String CONTENT_TYPE_TEXT_PLAIN = "text/plain; charset=" + CHARSET;
    String CONTENT_TYPE_MULTIPART = "multipart/form-data; charset=" + CHARSET;

    MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; " + CHARSET);
    MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; " + CHARSET);
    MediaType MEDIA_TYPE_TEXT_PLAIN = MediaType.parse("text/plain; " + CHARSET);
    MediaType MEDIA_TYPE_MULTIPART = MediaType.parse("multipart/form-data; " + CHARSET);
}
