/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:01 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: DateUtil.
 * Author: Lena; Last Modified: 1/19/16 3:01 PM.
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

package com.rayman.v2ex.utils;

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

    public static long getDiffDay(long start, long end) {
        long diff = end - start;
        return diff / (1000 * 60 * 60 * 24);
    }


    public static long getFinalLeftTime(long lastModified, long leftTime) {
        long period = System.currentTimeMillis() - lastModified;
        return leftTime - period / 1000;
    }

}
