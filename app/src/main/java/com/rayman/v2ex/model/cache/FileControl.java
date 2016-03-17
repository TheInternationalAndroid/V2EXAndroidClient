/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:00 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: FileControl.
 * Author: Lena; Last Modified: 1/19/16 3:00 PM.
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

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.rayman.v2ex.tools.LAsyncTask;
import com.rayman.v2ex.utils.DateUtil;
import com.rayman.v2ex.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

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

    private LinkedList<String> mFileLRU = new LinkedList<String>();
    private HashMap<String, OnFileChangedListener> mListenerHashMap = new HashMap<>();

    public void addCallBack(String key, OnFileChangedListener onFileChangedListener) {
        mListenerHashMap.put(key, onFileChangedListener);
    }

    public void removeCallBack(String key) {
        if (mListenerHashMap.containsKey(key))
            mListenerHashMap.remove(key);
    }

    @Inject public FileControl(Context context) {
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
                mFileLRU.remove(tmpFile.getAbsolutePath());
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
            mFileLRU.clear();
        }
    }

    public void clearImageCache() {
        FileUtil.deleteChild(sImageCacheRootPath);
    }

    @Override
    public <T> T get(String key) {
        LogUtil.i(LOG_TAG, "Get  " + key);
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
                synchronized (mFileLRU) {
                    mFileLRU.remove(filePath);
                    mFileLRU.addLast(filePath);
                }

                return value;
            } catch (FileNotFoundException ex) {
                // Will not happen.
                LogUtil.e(LOG_TAG, "File " + key + " not found");
            } catch (StreamCorruptedException ex) {
                remove(key);
                LogUtil.e(LOG_TAG, "Get File " + key + " cast StreamCorruptedException  ");
            } catch (IOException ex) {
                remove(key);
                LogUtil.e(LOG_TAG, "Get File " + key + " cast IOException  " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                // Will not happen.
                LogUtil.e(LOG_TAG, "Get File " + key + " cast ClassNotFoundException  ");
            } finally {
                if (objectStream != null) {
                    try {
                        objectStream.close();
                    } catch (IOException ex) {
                        // Do nothing.
                        LogUtil.e(LOG_TAG, "Get File " + key + " cast IOException");
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        // Do nothing.
                        LogUtil.e(LOG_TAG, "Get File " + key + " cast IOException");
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

    public synchronized <T> void putHistory(String key, T value, String newHistory) {
        put(key, value);
        if (mListenerHashMap.containsKey(key))
            mListenerHashMap.get(key).onChanged(newHistory);
    }

    @Override
    public synchronized <T> void put(String key, T value) {
        LogUtil.i(LOG_TAG, "Put  " + key);
        File file = getCacheFile(key);
        boolean fileExists = true;
        try {
            fileExists = !file.createNewFile();
        } catch (IOException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            // Will not happen.
        }

        FileOutputStream stream = null;
        ObjectOutputStream objectStream = null;
        try {
            stream = new FileOutputStream(file);
            objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(value);

            if (!fileExists) {
                mFileLRU.addLast(file.getAbsolutePath());
            }
        } catch (Exception ex) {
            if (ex.getMessage() != null) {
                Log.e(LOG_TAG, ex.getMessage());
            }
            // In case this happens, just ignore it.
        } finally {
            if (objectStream != null) {
                try {
                    objectStream.close();
                } catch (IOException ex) {
                    LogUtil.e(LOG_TAG, "Put File " + key + " cast IOException  " + ex.getMessage());
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    LogUtil.e(LOG_TAG, "Put File " + key + " cast IOException  " + ex.getMessage());
                }
            }
        }
    }

    public <T> void sysPut(final String key, final T value) {
        new LAsyncTask<Void, Void, Void>() {
            @Override protected void onPostExecute(Void aVoid) {

            }

            @Override protected Void doInBackground(Void... params) {
                try {
                    put(key, value);
                } catch (Exception e) {
                    LogUtil.e("getLocalCurrencyCountryList UnknownException" + e.getMessage());
                }
                return null;
            }
        }.executeParallel();
    }

    public <T> void sysGet(final String key, final OnFileLoaded<T> onFileLoaded) {
        new LAsyncTask<Void, Void, T>() {
            @Override protected void onPostExecute(T t) {
                onFileLoaded.onLoaded(t);
            }

            @Override protected T doInBackground(Void... params) {
                try {
                    return FileControl.this.get(key);
                } catch (Exception e) {
                    LogUtil.e("getLocalCurrencyCountryList UnknownException" + e.getMessage());
                }
                return null;
            }
        }.executeParallel();
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
            mFileLRU.remove(file.getAbsolutePath());
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
            LogUtil.e(LOG_TAG, "Create floder falure.Key = " + florderPath);
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
                    LogUtil.e(LOG_TAG, "Save Image cast Exception : " + e.getMessage());
                } finally {
                    try {
                        if (out != null)
                            out.close();
                    } catch (IOException e) {
                        LogUtil.e(LOG_TAG, "Save Image cast IOException : " + e.getMessage());
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

    private static class CacheOutputStream extends FileOutputStream {
        private int length;

        private FileChannel channel;

        public CacheOutputStream(File file, int length) throws FileNotFoundException {
            super(file);
            this.length = length;
            this.channel = getChannel();
        }

        @Override
        public void write(byte[] buffer) throws IOException {
            super.write(buffer);
            if (channel.size() >= length) {
                close();
            }
        }

        @Override
        public void write(byte[] buffer, int offset, int count) throws IOException {
            super.write(buffer, offset, count);
            if (channel.size() >= length) {
                close();
            }
        }

        @Override
        public void write(int oneByte) throws IOException {
            super.write(oneByte);
            if (channel.size() >= length) {
                close();
            }
        }
    }

    public interface OnFileLoaded<T> {
        void onLoaded(T result);
    }

}
