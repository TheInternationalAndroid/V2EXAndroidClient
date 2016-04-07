/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 10:03 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseActivity.
 * Author: Lena; Last Modified: 1/18/16 10:03 PM.
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

package com.rayman.v2ex.ui.view.base.page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rayman.v2ex.R;
import com.rayman.v2ex.ui.view.base.view.IView;
import com.rayman.v2ex.widget.anotations.ActivityAction;
import com.rayman.v2ex.widget.utils.ToastUtil;

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
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
            progressDialog.setContentView(R.layout.layout_progress);
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
    public void intentFinish(Bundle bundle, @ActivityAction int action) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(action, intent);
        finish();
    }

    protected boolean isCurrentlyOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

}
