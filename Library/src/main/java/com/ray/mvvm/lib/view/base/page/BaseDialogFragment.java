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

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.ray.mvvm.lib.R;
import com.ray.mvvm.lib.view.base.view.IView;
import com.ray.mvvm.lib.widget.anotations.ActivityAction;
import com.ray.mvvm.lib.widget.utils.ToastUtil;

public class BaseDialogFragment extends DialogFragment implements IView {
    private ProgressDialog progressDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void showToast(int stringRes) {
        ToastUtil.show(getActivity(), getString(stringRes));
    }

    @Override
    public void showToast(String string) {
        ToastUtil.show(getActivity(), string);
    }

    @Override
    public void showProgressDialog() {
        showProgressDialog(true);
    }

    @Override
    public void showProgressDialog(boolean cancelable) {
        if (!isFragmentActive()) return;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
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
        if (isFragmentActive() && progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
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
        Intent intent = new Intent(getActivity(), aClass);
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
        intent.setClass(getActivity(), aClass);
        startActivity(intent);
    }

    @Override
    public void intentForResult(Class aClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), aClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void intentFinish() {
        getActivity().finish();
    }

    @Override
    public void intentFinish(Intent intent, int action) {
        getActivity().setResult(action, intent);
        getActivity().finish();
    }

    @Override
    public void intentFinish(Bundle bundle, @ActivityAction int action) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        getActivity().setResult(action, intent);
        getActivity().finish();
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
    public String[] findStringRes(int resId) {
        return getResources().getStringArray(resId);
    }

    @Override
    public int findDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    @Override
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }

    @Override
    public void refreshOptionsMenu() {
        getActivity().invalidateOptionsMenu();
    }

    public boolean isFragmentActive() {
        return getActivity() != null && !isDetached() && isAdded();
    }

    @Override
    public void setSubTitle(String subTitle) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        if (appCompatActivity.getSupportActionBar() != null)
            appCompatActivity.getSupportActionBar().setSubtitle(subTitle);
    }

    @Override
    public int findColor(int resId) {
        return ContextCompat.getColor(getActivity(), resId);
    }

    @Override
    public void hideSoftwareInput() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showSoftwareInput() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    @Override
    public Drawable findDrawable(int resId) {
        return ContextCompat.getDrawable(getActivity(), resId);
    }

    @Override
    public void postRunnable(Runnable runnable) {
        View view = getView();
        if (view != null)
            view.post(runnable);
    }

}
