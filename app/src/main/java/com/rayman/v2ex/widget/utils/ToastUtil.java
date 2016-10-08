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

package com.rayman.v2ex.widget.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rayman.v2ex.R;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 22:55
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
public class ToastUtil {

    public static final int TOAST_DURATION_LONG = Toast.LENGTH_LONG;

    private static Toast mToast = null;

    public static void show(Context context, String message) {
        if (context == null) {
            return;
        }
        View viewContainer = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = (TextView) viewContainer;
        textView.setText(message);
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.BOTTOM, 0, 140);
        }
        mToast.setView(getToastView(message, context));
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void show(Context context, String message, int duration) {
        if (context == null) {
            return;
        }
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setGravity(Gravity.BOTTOM, 0, 140);
        }
        mToast.setView(getToastView(message, context));
        mToast.setDuration(duration);
        mToast.show();
    }

    private static View getToastView(String message, Context context) {
        View viewContainer = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        TextView textView = (TextView) viewContainer;
        textView.setText(message);
        return viewContainer;
    }

}
