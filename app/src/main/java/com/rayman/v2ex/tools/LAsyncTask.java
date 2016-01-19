/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:00 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: LAsyncTask.
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

package com.rayman.v2ex.tools;

import android.os.AsyncTask;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public abstract class LAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    @Override protected abstract void onPostExecute(Result result);

    public void executeParallel(Params... params) {
        Executor pool = Executors.newFixedThreadPool(10);
        executeOnExecutor(pool, params);
    }

}
