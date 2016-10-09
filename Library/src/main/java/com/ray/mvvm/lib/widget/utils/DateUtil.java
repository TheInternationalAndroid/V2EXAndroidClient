/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:08:29 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:08:29 +0800.
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

package com.ray.mvvm.lib.widget.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat simpleDateFormat;

    public static SimpleDateFormat getSimpleDateFormat(String format) {
        if (simpleDateFormat == null) simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat;
    }

    public static String formatDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = getSimpleDateFormat("yyyy-MM-dd-EEEE");
        return formatter.format(date);
    }

    public static String formatRecordDate(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static String formatDateStamp(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = getSimpleDateFormat("yyyyMMddHHmm");
        return formatter.format(date);
    }

    public static String formatDateyyMMddHHmm(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = getSimpleDateFormat("HH:mm:ss");
        return formatter.format(date);
    }

    public static String formatDateHHmm(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = getSimpleDateFormat("HH:mm");
        return formatter.format(date);
    }

    public static String formatDateMMddHHmm(long time) {
        Date date = new Date(time);
        SimpleDateFormat formatter = getSimpleDateFormat("MM/dd HH:mm");
        return formatter.format(date);
    }

    public static String getFormatPassTime(long lastTime) {
        long leftSecondsMM = System.currentTimeMillis() / 1000 - lastTime;
        if (leftSecondsMM > 0) {
            long minutes = leftSecondsMM / 60;
            long hours = minutes / 60;
            long remainder = minutes % 60;
            long day = hours / 24;
            if (day >= 1)
                return formatDateMMddHHmm(lastTime);
            if (hours >= 1)
                return hours + remainder >= 1 ? hours + "小时 " + remainder + "分钟前" : "小时前";
            if (minutes >= 1)
                return minutes + "分钟前";
            return "刚刚";
        }
        return "刚刚";
    }

}
