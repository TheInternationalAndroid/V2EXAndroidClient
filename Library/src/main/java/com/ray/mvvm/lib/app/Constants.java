/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sun, 9 Oct 2016 00:04:42 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sun, 9 Oct 2016 00:04:42 +0800.
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

package com.ray.mvvm.lib.app;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 18:09
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
public interface Constants {

    int ITEM_TYPE_NONE = 813;
    int NO_POSITION = -1;
    int TIMER_SEC_IN_MIN = 60;
    int PAGE_NUM_START = 1;
    int PAGE_SIZE_DEFAULT = 20;
    int MOMENT_PHOTO_MAX_COUNT = 4;
    int FEEDBACK_PHOTO_MAX_COUNT = 5;
    int VERSION_CHECK_INTERVAL = 1000 * 60 * 60;
    int BANNER_PLAY_INTERVAL = 1000 * 3;

    int FEEDBACK_INPUT_MAX = 500;
    int COMMENT_INPUT_MAX = 500;
    int SIGNATURE_INPUT_MAX = 100;
    int USERNAME_INPUT_MAX = 10;
    int PASSWORD_INPUT_MAX = 18;

    int MIN_NUM_PASSWORD = 6;
    int MIN_NUM_CODE = 4;
    int MIN_NUM_USERNAME = 2;

    String PARA_SHOW_GIF = "PARA_SHOW_GIF";
    String PARA_PHOTO_SELECTED_LIST_KEY = "PARA_PHOTO_SELECTED_LIST_KEY";
    String PARA_PHOTO_COUNT_LIMIT_KEY = "PARA_PHOTO_COUNT_LIMIT_KEY";
    String PARA_PHOTO_LIST_KEY = "PARA_PHOTO_LIST_KEY";
    String PARA_LOGIN_CALL_UP_KEY = "PARA_LOGIN_CALL_UP_KEY";
    String PARA_SAVED_INSTANCE_KEY = "PARA_SAVED_INSTANCE_KEY";
    String PARA_COUNT_KEY = "PARA_COUNT_KEY";
    String PARA_IMG_ARRAY_LIST_KEY = "PARA_IMG_ARRAY_LIST_KEY";

    String SP_LAST_LOGIN_PHONE = "SP_LAST_LOGIN_PHONE";
    String SP_TOKEN_KEY = "SP_TOKEN_KEY";
    String SP_USER_ID_KEY = "SP_USER_ID_KEY";
    String SP_POST_READ_POSITION_KEY = "SP_POST_READ_POSITION_KEY";
    String SP_POST_READ_POSITION_POST_ID_KEY = "SP_POST_READ_POSITION_POST_ID_KEY";
    int READ_POSITION_MIN = 400;

    String PARA_STRING_KEY = "PARA_STRING_KEY";
    String PARA_BOOLEAN_KEY = "PARA_BOOLEAN_KEY";
    String PARA_INTEGER_KEY = "PARA_INTEGER_KEY";
    String PARA_ID_KEY = "PARA_ID_KEY";
    String PARA_ARRAY_LIST_KEY = "PARA_ARRAY_LIST_KEY";

    String PARA_USER_NAME = "PARA_USER_NAME";
    String PARA_EDIT_CONTENT = "PARA_EDIT_CONTENT";

    String PAGE_TYPE_KEY = "PAGE_TYPE_KEY";

    String PARA_SOURCE_ID = "source_id";

    int FALSE = 0;
    int TRUE = 1;

    String SP_LAST_CHECK_UPDATE_TIME = "SP_LAST_CHECK_UPDATE_TIME";

    String SECRET_KEY = "65a231f31de9330780942b109692e5a1e2a779b51781349aaea2dae44958a1d644a803af21bbb318695e58b758f94e7aa3a5450a3f7092f6e05ea172d9b95043";
    String MIPUSH_CATEGORY = "IronHide";
    String TOPIC_LOGIN = "user";
    String TOPIC_UNLOGIN = "guest";

    String FILEPROVIDER_AUTHORITIES = "com.istuary.ironhide.fileprovider";
}
