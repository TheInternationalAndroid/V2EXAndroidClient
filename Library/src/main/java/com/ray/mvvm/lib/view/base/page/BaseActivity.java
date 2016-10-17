/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:47:37 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:47:37 +0800.
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

package com.ray.mvvm.lib.view.base.page;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ray.mvvm.lib.R;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.ActivityAction;
import com.ray.mvvm.lib.widget.utils.ToastUtil;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 22:03
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
public class BaseActivity extends AppCompatActivity implements IView {

    private ProgressDialog progressDialog;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
//            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(true);
    }

    @Override
    public void showProgressDialog(boolean cancelable) {
        if (isFinishing()) return;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(cancelable);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.setIndeterminate(true);
            progressDialog.show();
            progressDialog.setContentView(com.ray.mvvm.lib.R.layout.layout_progress);
        }
    }

    @Override
    public void hideProgressDialog() {
        if (isFinishing()) return;
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    @Override
    public void showToast(int stringRes) {
        ToastUtil.show(getApplicationContext(), getString(stringRes));
    }

    @Override
    public void showToast(String string) {
        ToastUtil.show(getApplicationContext(), string);
    }

    protected void initViews(int layoutResID) {
        initViews(layoutResID, true);
    }

    protected void initViews(int layoutResID, boolean homeAsUp) {
        setContentView(layoutResID);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUp);
            }
        }
    }

    @Override
    public void intent(Class aClass) {
        intent(aClass, null);
    }

    @Override
    public void intentForResult(Class aClass, int requestCode) {
        intentForResult(aClass, requestCode, null);
    }

    @Override
    public void intent(Class aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void intent(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void intent(Intent intent, Class<?> aClass) {
        intent.setClass(this, aClass);
        startActivity(intent);
    }

    @Override
    public void intentForResult(Class aClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void intentFinish() {
        finish();
    }

    @Override
    public void intentFinish(Intent intent, int action) {
        setResult(action, intent);
        finish();
    }

    @Override
    public void intentFinish(Bundle bundle, @ActivityAction int action) {
        Intent intent = getIntent();
        if (bundle != null)
            intent.putExtras(bundle);
        setResult(action, intent);
        finish();
    }

    @Override
    public void intentFinishNewTask(Class<?> aClass) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent(intent, aClass);
    }

    @Override
    public String findString(int resId) {
        return super.getString(resId);
    }

    @Override
    public Drawable findDrawable(int resId) {
        return ContextCompat.getDrawable(this, resId);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void refreshOptionsMenu() {
        invalidateOptionsMenu();
    }

    protected boolean isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @Override
    public void setSubTitle(String subTitle) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setSubtitle(subTitle);
    }

    @Override
    public String[] findStringRes(int resId) {
        return getResources().getStringArray(resId);
    }

    @Override
    public int findDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    @Override
    public int findColor(int resId) {
        return ContextCompat.getColor(this, resId);
    }

    @Override
    public void hideSoftwareInput() {
        View view = this.getCurrentFocus();
        if (view != null) {
            view.post(() -> {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            });
        }
    }

    @Override
    public void showSoftwareInput() {
        View view = this.getCurrentFocus();
        if (view != null) {
            view.post(() -> {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            });
        }
    }

    @Override
    public void postRunnable(Runnable runnable) {
        this.getWindow().getDecorView().post(runnable);
    }

    public void setupTouchOutSideUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                hideSoftwareInput();
                return false;
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupTouchOutSideUI(innerView);
            }
        }
    }
}
