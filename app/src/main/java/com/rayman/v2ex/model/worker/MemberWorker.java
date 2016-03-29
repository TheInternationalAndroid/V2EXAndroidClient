package com.rayman.v2ex.model.worker;

import com.rayman.v2ex.model.http.service.MemberService;
import com.rayman.v2ex.model.model.member.MemberEntity;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 9:49 PM
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
public class MemberWorker extends BaseWorker {

    private MemberService memberService;

    @Inject
    public MemberWorker(MemberService memberService) {
        this.memberService = memberService;
    }

    public void member(String userName, Subscriber<MemberEntity> subscriber) {
        defaultCall(memberService.member(userName), subscriber);
    }

}
