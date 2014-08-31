/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.learning.mail;

import java.util.Properties;
import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author anonymous
 */
public class EmailSender {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SOURCE_EMAIL = "SOURCE_EMAIL";
    private static final String SOURCE_EMAIL_PASSWORD = "SOURCE_EMAIL_PASSWORD";
    private static final String DESTINATION_EMAIL = "DESTINATION_EMAIL";

    private static final String emailSubject = "test send email";
    private static final String emailMessage = "test!!!";

    static {
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }

    public static void main(String args[]) throws Exception {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        
        
        Properties pro = new Properties();
        pro.setProperty("mail.smtps.auth", "true");
        pro.setProperty("mail.smtps.starttls.enable", "true");
        pro.setProperty("mail.debug", "true");

        sender.setJavaMailProperties(pro);
        sender.setUsername(SOURCE_EMAIL);
        sender.setPassword(SOURCE_EMAIL_PASSWORD);
        sender.setProtocol("smtps");
        sender.setPort(465);
        sender.setHost(SMTP_HOST_NAME);
        sender.setDefaultEncoding("utf-8");

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setSubject(emailSubject);
        helper.setText(emailMessage);

        helper.setTo(DESTINATION_EMAIL);
        sender.send(message);
    }
}
