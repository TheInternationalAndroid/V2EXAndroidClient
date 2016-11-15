/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 21:53:50 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 21:53:50 +0800.
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

package com.ray.mvvm.lib.model.model.topic;

import android.os.Parcel;
import android.os.Parcelable;

import com.ray.mvvm.lib.model.model.member.MemberBaseEntity;
import com.ray.mvvm.lib.model.model.node.NodeEntity;
import com.squareup.moshi.Json;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 17:51
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
public class TopicEntity implements Parcelable {

    private long id;
    private String title;
    private String url;
    private String content;
    @Json(name = "content_rendered")
    private String contentRendered;
    private int replies;
    private MemberBaseEntity member;
    private NodeEntity node;
    private long created;
    @Json(name = "last_modified")
    private long lastModified;
    @Json(name = "last_touched")
    private long lastTouched;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentRendered() {
        return contentRendered;
    }

    public void setContentRendered(String contentRendered) {
        this.contentRendered = contentRendered;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public MemberBaseEntity getMember() {
        return member;
    }

    public void setMember(MemberBaseEntity member) {
        this.member = member;
    }

    public NodeEntity getNode() {
        return node;
    }

    public void setNode(NodeEntity node) {
        this.node = node;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public long getLastTouched() {
        return lastTouched;
    }

    public void setLastTouched(long lastTouched) {
        this.lastTouched = lastTouched;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.content);
        dest.writeString(this.contentRendered);
        dest.writeInt(this.replies);
        dest.writeParcelable(this.member, flags);
        dest.writeParcelable(this.node, flags);
        dest.writeLong(this.created);
        dest.writeLong(this.lastModified);
        dest.writeLong(this.lastTouched);
    }

    public TopicEntity() {
    }

    protected TopicEntity(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.url = in.readString();
        this.content = in.readString();
        this.contentRendered = in.readString();
        this.replies = in.readInt();
        this.member = in.readParcelable(MemberBaseEntity.class.getClassLoader());
        this.node = in.readParcelable(NodeEntity.class.getClassLoader());
        this.created = in.readLong();
        this.lastModified = in.readLong();
        this.lastTouched = in.readLong();
    }

    public static final Creator<TopicEntity> CREATOR = new Creator<TopicEntity>() {
        @Override
        public TopicEntity createFromParcel(Parcel source) {
            return new TopicEntity(source);
        }

        @Override
        public TopicEntity[] newArray(int size) {
            return new TopicEntity[size];
        }
    };
}
