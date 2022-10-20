package com.berk.todoapp.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class MailSenderController {

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    private Properties props = mailSender.getJavaMailProperties();

    public MailSenderController(){
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("javamaildenemem@gmail.com");
        mailSender.setPassword("ltgffanqkrwoanti");

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

    }

    public void sendMail(String receipent){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receipent);
        message.setFrom("javamaildenemem@gmail.com");
        message.setSubject("Hoşgeldiniz");
        message.setText("Kayıt olduğunuz için teşekkür ederiz.");
        mailSender.send(message);
    }




}