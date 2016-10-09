/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:38:42 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:38:42 +0800.
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

package com.ray.mvvm.lib.model.http.adapter;

import com.ray.mvvm.lib.model.model.NullEntity;
import com.ray.mvvm.lib.model.model.RespEntity;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/**
 * Created by Android Studio.
 * ProjectName: IronHide
 * Author:  Lena
 * Date: 6/27/16
 * Time: 5:01 PM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class RespEntityAdapter {

    @ToJson
    public RespEntity<NullEntity> toJson(RespEntity respEntity) {
        RespEntity<NullEntity> entity = new RespEntity<>();
        entity.setCode(respEntity.getCode());
        entity.setMessage(respEntity.getMessage());
        entity.setData(new NullEntity());
        return entity;
    }

    @FromJson
    public RespEntity fromJson(RespEntity<NullEntity> respEntity) {
        RespEntity entity = new RespEntity();
        entity.setCode(respEntity.getCode());
        entity.setMessage(respEntity.getMessage());
        return entity;
    }


}
