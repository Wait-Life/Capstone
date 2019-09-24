package com.codeup.drinkhustle.Services;

import com.codeup.drinkhustle.Models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("EmailService")
public class EmailService {

    @Autowired
    public JavaMailSender emailSender;

    @Value("admin@email.com")
    private String from;

    public void prepareAndSend (Event event, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(event.getOwner().getEmail());
        msg.setSubject(subject);
        msg.setText(body);
        try {
            this.emailSender.send(msg);
        } catch (MailException m) {
            System.err.println(m.getMessage());
        }
    }
}
