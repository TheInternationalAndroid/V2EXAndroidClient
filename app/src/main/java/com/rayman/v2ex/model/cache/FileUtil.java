/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 2:58 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: FileUtil.
 * Author: Lena; Last Modified: 1/19/16 2:58 PM.
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

package com.rayman.v2ex.model.cache;


import com.rayman.v2ex.widget.utils.LogUtil;

import java.io.File;

public class FileUtil {

    private static final String TAG = "FileUtil";

    public static boolean delete(String fileName) {
        return delete(new File(fileName));
    }

    public static boolean delete(File file) {
        if (!file.exists()) {
            LogUtil.i(TAG + "Delete " + file.getPath() + " Fail.");
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(file.getPath());
            } else {
                return deleteDirectory(file.getPath(), true);
            }
        }
    }

    public static boolean deleteChild(String fileName) {
        return deleteChild(new File(fileName));
    }

    public static boolean deleteChild(File file) {
        if (!file.exists()) {
            LogUtil.i(TAG + "Delete Child " + file.getPath() + " Fail.");
            return false;
        } else {
            if (file.isFile()) {
                LogUtil.i(TAG + "Delete Child in" + file.getPath() + " Fail," + file.getPath() + " is not a directory.");
                return false;
            } else {
                return deleteDirectory(file.getPath(), false);
            }
        }
    }

    public static boolean deleteFile(String fileName) {
        return deleteFile(new File(fileName));
    }

    public static boolean deleteFile(File file) {
        if (file.isFile() && file.exists()) {
            return file.delete();
        } else {
            LogUtil.i(TAG + "Delete " + file.getPath() + " Fail");
            return false;
        }
    }

    public static boolean deleteDirectory(String dir, boolean deleteSelf) {
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        return deleteDirectory(new File(dir), deleteSelf);
    }

    public static boolean deleteDirectory(File dirFile, boolean deleteSelf) {
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            LogUtil.i(TAG + "Derectory" + dirFile.getPath() + " not exist");
            return false;
        }
        boolean flag = true;
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } else {
                flag = deleteDirectory(files[i].getAbsolutePath(), true);
                if (!flag) {
                    break;
                }
            }
        }

        if (!flag) {
            LogUtil.i(TAG + "Delete Derectory " + dirFile.getPath() + " Fail");
            return false;
        }
        if (deleteSelf) {
            if (dirFile.delete()) {
                return true;
            } else {
                LogUtil.i(TAG + "Delete Derectory " + dirFile.getPath() + " Fail");
                return false;
            }
        }
        return true;
    }
}
