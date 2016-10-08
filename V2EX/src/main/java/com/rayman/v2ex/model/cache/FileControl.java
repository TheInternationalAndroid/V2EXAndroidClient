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

package com.rayman.v2ex.model.cache;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import com.rayman.v2ex.widget.utils.DateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class FileControl implements IFileControl {

    public interface OnFileChangedListener<T> {
        void onChanged(T o);
    }

    public static final String IMAGE_CACHE_FLODER_SUFFIX = "/ImageCache";
    public static final String FILE_CACHE_FLODER_SUFFIX = "/FileControl";
    public static final String FILE_DOWNLOAD_FLODER_SUFFIX = "/files";
    private static final String REQUEST_CACHE_FLODER_SUFFIX = "/HttpCache";
    private static final String LOG_TAG = "FileControl";
    private static final String FILE_CACHE_VERSION_CODE_KEY = "FILE_CACHE_VERSION_CODE_KEY";
    private static final String FILE_CACHE_VERSION_CODE = "2016-1-20";
    private static final byte[] LRU_LOCK = new byte[0];
    public static int REMOVE_CACHE_FILE_NOT_EXISTS = -1;
    public static int REMOVE_CACHE_SUCCESS = 1;
    public static int REMOVE_CACHE_FAILURE = 0;

    public String sRoot;
    public String sCacheRoot;
    public String sImageCacheRootPath;
    public String sFileCacheRootPath;
    public String sDownLoadFileRootPath;

    private LinkedList<String> fileLRU = new LinkedList<>();
    private HashMap<String, OnFileChangedListener> listenerHashMap = new HashMap<>();

    public void addCallBack(String key, OnFileChangedListener onFileChangedListener) {
        listenerHashMap.put(key, onFileChangedListener);
    }

    public void removeCallBack(String key) {
        if (listenerHashMap.containsKey(key))
            listenerHashMap.remove(key);
    }

    @Inject
    public FileControl(Context context) {
        install(context);
        cacheCheck();
    }

    public void install(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // SD-card available
            sRoot = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/Android/data/" + context.getPackageName();
            sCacheRoot = sRoot + "/cache";
            File file = new File(sCacheRoot);
            if (!file.exists()) {
                if (!file.mkdirs())
                    sCacheRoot = context.getCacheDir().getAbsolutePath();
            }
        } else {
            sCacheRoot = context.getCacheDir().getAbsolutePath();
        }
        initImageCacheFile();
        initFileCacheFile();
        initDownLoadFilesPath();
    }

    private void initImageCacheFile() {
        sImageCacheRootPath = sCacheRoot + IMAGE_CACHE_FLODER_SUFFIX;
        File file = new File(sImageCacheRootPath);
        if (!file.exists()) {
            if (!file.mkdirs())
                sImageCacheRootPath = sCacheRoot;
        }
    }

    private void initFileCacheFile() {
        sFileCacheRootPath = sCacheRoot + FILE_CACHE_FLODER_SUFFIX;
        File file = new File(sFileCacheRootPath);
        if (!file.exists()) {
            if (!file.mkdirs())
                sFileCacheRootPath = sCacheRoot;
        }
    }

    private void initDownLoadFilesPath() {
        sDownLoadFileRootPath = sRoot + FILE_DOWNLOAD_FLODER_SUFFIX;
        File file = new File(sDownLoadFileRootPath);
        if (!file.exists()) {
            if (!file.mkdirs())
                sDownLoadFileRootPath = sRoot;
        }
    }

    @Override
    public String requestCacheFloderPath() {
        return sFileCacheRootPath + REQUEST_CACHE_FLODER_SUFFIX;
    }

    @Override
    public void cacheCheck() {
        String versionCode = get(FILE_CACHE_VERSION_CODE_KEY);
        if (versionCode == null || !versionCode.equals(FILE_CACHE_VERSION_CODE)) {
            cleanCacheUpdateVersion();
        }
    }

    @Override
    public void cleanCacheUpdateVersion() {
        File file = new File(sFileCacheRootPath);
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            put(FILE_CACHE_VERSION_CODE_KEY, FILE_CACHE_VERSION_CODE);
            return;
        }
        File tmpFile = null;
        for (int i = 0; i < files.length; i++) {
            tmpFile = new File(files[i].getAbsolutePath());
            FileUtil.delete(tmpFile);
            synchronized (LRU_LOCK) {
                fileLRU.remove(tmpFile.getAbsolutePath());
            }
        }
        put(FILE_CACHE_VERSION_CODE_KEY, FILE_CACHE_VERSION_CODE);
    }

    public void clear() {
        clearFileCache();
        clearImageCache();
    }

    public void clearFileCache() {
        FileUtil.deleteChild(sFileCacheRootPath);
        synchronized (LRU_LOCK) {
            fileLRU.clear();
        }
    }

    public void clearImageCache() {
        FileUtil.deleteChild(sImageCacheRootPath);
    }

    @Override
    public <T> T get(String key) {
        Timber.i("Get%s", key);
        File file = getCacheFile(key);
        if (file.exists()) {
            FileInputStream stream = null;
            ObjectInputStream objectStream = null;
            try {
                stream = new FileInputStream(file);
                objectStream = new ObjectInputStream(stream);
                @SuppressWarnings("unchecked")
                T value = (T) objectStream.readObject();

                // Update position within LRU.
                String filePath = file.getAbsolutePath();
                synchronized (fileLRU) {
                    fileLRU.remove(filePath);
                    fileLRU.addLast(filePath);
                }

                return value;
            } catch (FileNotFoundException ex) {
                // Will not happen.
                Timber.e("File %s %s", key, " not found");
            } catch (StreamCorruptedException ex) {
                remove(key);
                Timber.e("Get File %s %s", key, " cast StreamCorruptedException  ");
            } catch (IOException ex) {
                remove(key);
                Timber.e("Get File  %s %s %s", key, " cast IOException  ", ex.getMessage());
            } catch (ClassNotFoundException ex) {
                // Will not happen.
                Timber.e("Get File  %s %s", key, " cast ClassNotFoundException  ");
            } finally {
                if (objectStream != null) {
                    try {
                        objectStream.close();
                    } catch (IOException ex) {
                        // Do nothing.
                        Timber.e("Get File %s %s", key, " cast IOException");
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        // Do nothing.
                        Timber.e("Get File %s %s", key, " cast IOException");
                    }
                }
            }
        }
        return null;
    }

    @Override
    public <T> T get(String key, T defaultValue) {
        T item = get(key);
        if (item == null) {
            return defaultValue;
        } else {
            return item;
        }
    }

    @Override
    public synchronized <T> void put(String key, T value) {
        Timber.i("Put  %s", key);
        File file = getCacheFile(key);
        boolean fileExists = true;
        try {
            fileExists = !file.createNewFile();
        } catch (IOException ex) {
            Timber.e(ex.getMessage());
            // Will not happen.
        }

        FileOutputStream stream = null;
        ObjectOutputStream objectStream = null;
        try {
            stream = new FileOutputStream(file);
            objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(value);

            if (!fileExists) {
                fileLRU.addLast(file.getAbsolutePath());
            }
        } catch (Exception ex) {
            if (ex.getMessage() != null) {
                Timber.e(ex.getMessage());
            }
            // In case this happens, just ignore it.
        } finally {
            if (objectStream != null) {
                try {
                    objectStream.close();
                } catch (IOException ex) {
                    Timber.e("Put File %s %s %s", key, " cast IOException  ", ex.getMessage());
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    Timber.e("Put File %s %s %s ", key, " cast IOException  ", ex.getMessage());
                }
            }
        }
    }

    @Override
    public int remove(String key) {
        if (!exists(key)) {
            return REMOVE_CACHE_FILE_NOT_EXISTS;
        }
        File file = getCacheFile(key);
        if (!FileUtil.delete(file)) {
            file.deleteOnExit();
        }
        synchronized (LRU_LOCK) {
            fileLRU.remove(file.getAbsolutePath());
        }
        if (exists(key)) {
            return REMOVE_CACHE_FAILURE;
        }
        return REMOVE_CACHE_SUCCESS;
    }

    @Override
    public boolean exists(String key) {
        return getCacheFile(key).exists();
    }

    public boolean isDownLoadFileexists(String key) {
        return new File(getValidFloderPath(sDownLoadFileRootPath), escapeFileName(key)).exists();
    }

    public String getDownLoadFileFullPath(String key) {
        return sDownLoadFileRootPath + "/" + key;
    }

    public File getCacheFile(String key) {
        return new File(getValidFloderPath(sFileCacheRootPath), escapeFileName(key));
    }

    public File getCacheFile(String key, String roortPath) {
        return new File(getValidFloderPath(roortPath), escapeFileName(key));
    }

    public File getCacheImageFile(String key) {
        return new File(getValidFloderPath(sImageCacheRootPath), escapeFileName(key));
    }

    public File getCacheImageFile(String key, String roortPath) {
        return new File(getValidFloderPath(roortPath), escapeFileName(key));
    }

    public String getValidFloderPath(String florderPath) {
        File file = new File(florderPath);
        if (!file.exists() && !file.mkdirs()) {
            Timber.e("Create floder falure.Key = %s", florderPath);
        }
        return florderPath;
    }

    private String escapeFileName(String key) {
        return key.replace('/', '_');
    }

    public boolean isFileCacheExpired(String key, long cacheTime) {
        long lastModified = getFileLastModifiedTime(key);
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        long period = calendar.getTime().getTime() - lastModified;
        if (period > cacheTime) {
            return true;
        } else {
            return false;
        }
    }

    public long getFileLastModifiedTime(String key) {
        File file = new File(sFileCacheRootPath, escapeFileName(key));
        if (file.exists()) {
            return file.lastModified();
        }
        return 0;
    }

    public void saveImageFile(final String fileName, final Bitmap bitmap) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream out = null;
                try {
                    File imageFile = getCacheFile(fileName + ".jpg", sImageCacheRootPath);
                    out = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                } catch (Exception e) {
                    e.printStackTrace();
                    Timber.e("Save Image cast Exception : %s", e.getMessage());
                } finally {
                    try {
                        if (out != null)
                            out.close();
                    } catch (IOException e) {
                        Timber.e("Save Image cast IOException : %s", e.getMessage());
                    }
                }
            }
        }).start();
    }

    public String getImageFileCachePath(Context context, String fileName) {
        String path;
        if (isIntentAvailable(context, MediaStore.ACTION_IMAGE_CAPTURE)) {
            path = sImageCacheRootPath + "/" + fileName + DateUtil.formatDate(System.currentTimeMillis())
                    + ".jpg";
        } else {
            path = sImageCacheRootPath + "/temp" + DateUtil.formatDate(System.currentTimeMillis()) + ".jpg";
        }
        return "file:///" + path;
    }

    private boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

}
