/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 22:22:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 22:22:35 +0800.
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

package com.rayman.v2ex.model.model.node;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 17:57
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
public class NodeEntity implements Parcelable {

    private long id;
    private String name;
    private String title;
    @JSONField(name = "title_alternative")
    private String titleAlternative;
    private String url;
    private int topics;
    @JSONField(name = "avatar_mini")
    private String avatarMini;
    @JSONField(name = "avatar_normal")
    private String avatarNormal;
    @JSONField(name = "avatar_large")
    private String avatarLarge;
    private String header;
    private String footer;
    private long created;
    private long stars;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleAlternative() {
        return titleAlternative;
    }

    public void setTitleAlternative(String titleAlternative) {
        this.titleAlternative = titleAlternative;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTopics() {
        return topics;
    }

    public void setTopics(int topics) {
        this.topics = topics;
    }

    public String getAvatarMini() {
        return avatarMini;
    }

    public void setAvatarMini(String avatarMini) {
        this.avatarMini = avatarMini;
    }

    public String getAvatarNormal() {
        return avatarNormal;
    }

    public void setAvatarNormal(String avatarNormal) {
        this.avatarNormal = avatarNormal;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getStars() {
        return stars;
    }

    public void setStars(long stars) {
        this.stars = stars;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.title);
        dest.writeString(this.titleAlternative);
        dest.writeString(this.url);
        dest.writeInt(this.topics);
        dest.writeString(this.avatarMini);
        dest.writeString(this.avatarNormal);
        dest.writeString(this.avatarLarge);
        dest.writeString(this.header);
        dest.writeString(this.footer);
        dest.writeLong(this.created);
        dest.writeLong(this.stars);
    }

    public NodeEntity() {
    }

    protected NodeEntity(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.title = in.readString();
        this.titleAlternative = in.readString();
        this.url = in.readString();
        this.topics = in.readInt();
        this.avatarMini = in.readString();
        this.avatarNormal = in.readString();
        this.avatarLarge = in.readString();
        this.header = in.readString();
        this.footer = in.readString();
        this.created = in.readLong();
        this.stars = in.readLong();
    }

    public static final Creator<NodeEntity> CREATOR = new Creator<NodeEntity>() {
        @Override
        public NodeEntity createFromParcel(Parcel source) {
            return new NodeEntity(source);
        }

        @Override
        public NodeEntity[] newArray(int size) {
            return new NodeEntity[size];
        }
    };
}
