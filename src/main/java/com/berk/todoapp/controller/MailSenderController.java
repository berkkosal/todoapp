package com.berk.todoapp.controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSenderController {

    public static void sendMail(String recepient) throws MessagingException {
        System.out.println("Mail göndermeye hazırlanıyor.");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String hostEmail = "Javamaildenemem@gmail.com";
        String hostMailPass = "Java123456789";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(hostEmail, hostMailPass);
            }
        });

        Message message = prepareMessage(session, hostEmail, recepient);
        Transport.send(message);
        System.out.println("Mail gönderildi.");


    }

    private static Message prepareMessage(Session session, String hostEmail, String recepient) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(hostEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Konu bir deneme");
            message.setText("Deneme içerikli yazı.\n Nasıl harika değil mi?");
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}




