/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.view.main.vm;

import android.view.View;

import com.ray.mvvm.lib.view.web.WebViewActivity;
import com.ray.mvvm.lib.viewmodel.BaseVM;
import com.ray.sample.v2ex.view.main.contract.MainContract;
import com.ray.sample.v2ex.view.mock.MockSamplesActivity;
import com.ray.sample.v2ex.view.v2ex.TopicListActivity;

public class MainVM extends BaseVM<MainContract.Presenter, MainContract.View> {

    public MainVM(MainContract.Presenter presenter, MainContract.View view) {
        super(presenter, view);
    }

    public void onV2exClicked(View view) {
        this.view.intent(TopicListActivity.class);
    }

    public void onMockDataClicked(View view) {
        this.view.intent(MockSamplesActivity.class);
    }

    public void onWebViewClicked(View view) {
        WebViewActivity.intent(this.view, "Bing", "http://bing.com");
    }
}
