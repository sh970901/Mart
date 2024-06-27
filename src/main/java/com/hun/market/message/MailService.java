package com.hun.market.message;

public interface MailService extends MessageService{
    void sendMail(String to, String subject, String content) throws Exception;
}
