package com.hun.market.message.service;

public interface MailService extends MessageService{
    void sendMail(String to, String subject, String content) throws Exception;
}
