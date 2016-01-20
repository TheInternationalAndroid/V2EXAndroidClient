/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 6:10 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseStateVM.
 * Author: Lena; Last Modified: 1/19/16 6:10 PM.
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

package com.rayman.v2ex.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.google.repacked.apache.commons.lang3.StringUtils;
import com.rayman.v2ex.R;
import com.rayman.v2ex.anotations.PageState;
import com.rayman.v2ex.anotations.ViewClick;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 18:10
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
public abstract class BaseStateVM extends BaseObservable {

    private int state;
    private String emptyString;
    private String errorString;

    @Bindable public int getState() {
        return state;
    }

    public void setState(@PageState int state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    @Bindable public String getEmptyString() {
        return emptyString;
    }

    public void setEmptyString(String emptyString) {
        this.emptyString = emptyString;
        notifyPropertyChanged(BR.emptyString);
    }

    @Bindable public String getErrorString() {
        return errorString;
    }

    protected void showError(@PageState int state, String message) {
        setState(state);
        setErrorString(message);
    }

    public void setErrorString(String errorString) {
        if (StringUtils.isEmpty(errorString)) return;
        this.errorString = errorString;
        notifyPropertyChanged(BR.errorString);
    }

    @ViewClick(R.id.btn_retry) public abstract void onRetryClicked(View view);

}
