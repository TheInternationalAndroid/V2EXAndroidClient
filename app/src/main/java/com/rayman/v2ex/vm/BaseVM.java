/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 11:16 AM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseVM.
 * Author: Lena; Last Modified: 3/2/16 11:16 AM.
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

import com.rayman.v2ex.presenter.BasePresenter;
import com.rayman.v2ex.presenter.IPage;

public class BaseVM<T extends BasePresenter> extends BaseObservable implements IPage {

    protected T presenter;

    protected T presenter() {
        return presenter;
    }

    public BaseVM(T presenter) {
        this.presenter = presenter;
    }

    @Override public void onViewAttach() {
        if (presenter != null)
            presenter.onViewAttach();
    }

    @Override public void onViewDetach() {
        if (presenter != null)
            presenter.onViewDetach();
    }
}
