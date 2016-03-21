package com.rayman.v2ex.model.model.reply;

import com.alibaba.fastjson.annotation.JSONField;
import com.rayman.v2ex.model.model.member.MemberBaseEntity;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 8:16 PM
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
public class ReplyEntity {

    private long id;
    private String thanks;
    private String content;
    @JSONField(name = "content_rendered")
    private String contentRendered;
    private MemberBaseEntity member;
    private long created;
    @JSONField(name = "last_modified")
    private long lastModified;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThanks() {
        return thanks;
    }

    public void setThanks(String thanks) {
        this.thanks = thanks;
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

    public MemberBaseEntity getMember() {
        return member;
    }

    public void setMember(MemberBaseEntity member) {
        this.member = member;
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
}
