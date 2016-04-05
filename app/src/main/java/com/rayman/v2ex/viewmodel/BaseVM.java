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

package com.rayman.v2ex.viewmodel;

import android.databinding.BaseObservable;

public class BaseVM<T, R> extends BaseObservable {

    protected final T presenter;
    protected final R view;

    public BaseVM(T presenter, R view) {
        this.presenter = presenter;
        this.view = view;
    }

    public T presenter() {
        return presenter;
    }

    public R view() {
        return view;
    }
}
