package com.hun.market.member.event;

import com.hun.market.message.service.MailService;

public class MbResetRandomPasswordEvent implements MemberEvent {

    private String email;
    private String randomPassword;

    public MbResetRandomPasswordEvent(String email, String randomPassword){
        this.randomPassword = randomPassword;
        this.email=email;
    }

    @Override
    public void process() {

    }

    public void process(MailService mailService) throws Exception {
        String subject = "이노마켓 신규 패스워드 발급";
        String content = randomPassword;
        mailService.sendMail(email, subject, content);
    }
}