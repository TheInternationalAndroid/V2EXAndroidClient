/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 3:03 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: MemberEntity.
 * Author: Lena; Last Modified: 1/20/16 3:03 PM.
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

package com.rayman.v2ex.model.member;

import android.os.Parcel;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 15:03
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
public class MemberEntity extends MemberBaseEntity {

    private String status;
    private String url;
    private String website;
    private String twitter;
    private String psn;
    private String github;
    private String btc;
    private String location;
    private String bio;
    private long created;

    public MemberEntity() {
    }

    public MemberEntity(MemberBaseEntity memberBaseEntity) {
        this.id = memberBaseEntity.id;
        this.username = memberBaseEntity.username;
        this.tagline = memberBaseEntity.tagline;
        this.avatarMini = memberBaseEntity.avatarMini;
        this.avatarNormal = memberBaseEntity.avatarNormal;
        this.avatarLarge = memberBaseEntity.avatarLarge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getBtc() {
        return btc;
    }

    public void setBtc(String btc) {
        this.btc = btc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.status);
        dest.writeString(this.url);
        dest.writeString(this.website);
        dest.writeString(this.twitter);
        dest.writeString(this.psn);
        dest.writeString(this.github);
        dest.writeString(this.btc);
        dest.writeString(this.location);
        dest.writeString(this.bio);
        dest.writeLong(this.created);
    }

    protected MemberEntity(Parcel in) {
        super(in);
        this.status = in.readString();
        this.url = in.readString();
        this.website = in.readString();
        this.twitter = in.readString();
        this.psn = in.readString();
        this.github = in.readString();
        this.btc = in.readString();
        this.location = in.readString();
        this.bio = in.readString();
        this.created = in.readLong();
    }

    public static final Creator<MemberEntity> CREATOR = new Creator<MemberEntity>() {
        public MemberEntity createFromParcel(Parcel source) {
            return new MemberEntity(source);
        }

        public MemberEntity[] newArray(int size) {
            return new MemberEntity[size];
        }
    };
}
