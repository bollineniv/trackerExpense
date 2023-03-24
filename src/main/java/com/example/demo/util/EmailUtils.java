package com.example.demo.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmailWithAttachment(String email, String subject, String content, String reciptName, byte[] reciept) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(email);

        helper.setSubject(subject);

        helper.setText(content, true);

        helper.addAttachment(reciptName, new ByteArrayResource(reciept));

        javaMailSender.send(msg);

    }
}
