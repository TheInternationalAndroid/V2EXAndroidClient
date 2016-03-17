/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 6:07 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: OnPageStateListener.
 * Author: Lena; Last Modified: 1/19/16 6:07 PM.
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

package com.rayman.v2ex.ui.view.base;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 18:07
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
public interface OnPageStateListener {

    void onRetryClicked();

    void hideContent();

    void showContent();

    void onEmptyAddClicked();

    void setEmptyMessage(int stringRes);

    boolean isLoading();

    void showEmptyView();

    void showEmptyView(boolean showButton);

    void showEmptyView(int stringRes);

    void showEmptyView(int stringRes, boolean showButton);

    void showErrorVew();

    void showLoadingView();

    void hideEmptyView();

    void hideErrorView();

    void hideLoadingView();

    void hideStateViews();

}
