/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on 1/20/16 12:18 PM
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: 1/20/16 12:18 PM.
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

package com.rayman.v2ex;

import android.os.Bundle;
import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import com.rayman.v2ex.model.model.member.MemberBaseEntity;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/8/16
 * Time: 12:47 PM
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
@RunWith(AndroidJUnit4.class)
public class InstrumentTest {

    private static final long TEST_LONG = 12345678L;
    private static final String TEST_KEY = "TEST_KEY";
    private MemberBaseEntity memberBaseEntity;

    @Before
    public void createLogHistory() {
        memberBaseEntity = new MemberBaseEntity();
    }

    @Test
    public void logHistory_ParcelableWriteRead() {
        // Set up the Parcelable object to send and receive.
        memberBaseEntity.setId(TEST_LONG);

        // Write the data.
        Parcel parcel = Parcel.obtain();
        memberBaseEntity.writeToParcel(parcel, memberBaseEntity.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        Bundle bundle = new Bundle();
        bundle.putParcelable(TEST_KEY, memberBaseEntity);
        MemberBaseEntity newMemberBaseEntity = bundle.getParcelable(TEST_KEY);
        Assert.assertThat(newMemberBaseEntity.getId(), Is.is(TEST_LONG));
    }
}
