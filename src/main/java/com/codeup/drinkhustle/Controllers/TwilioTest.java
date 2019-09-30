package com.codeup.drinkhustle.Controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
//import com.google.api.client.util.Value;


@Controller
public class TwilioTest {

//     Values //
    @Value("${twilio-acct-sid}")
    private String twilioSid;

    @Value("${twilio-auth-token}")
    private String twilioToken;

    // Constructor //
    public TwilioTest() { }

    // Getters //
    public String getTwilioSid() { return twilioSid; }

    public String getTwilioToken() { return twilioToken; }


    // Test //
    public static void main(String[] args) {

        TwilioTest twilioTest = new TwilioTest();

        Twilio.init(twilioTest.getTwilioSid(), twilioTest.getTwilioToken());
        Message message = Message.creator(new PhoneNumber("+12102743221"), new PhoneNumber("+12815576961"), "Test success!").create();

        System.out.println(message.getSid());
    }
}

