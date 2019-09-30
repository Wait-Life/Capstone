package com.codeup.drinkhustle.Controllers;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioTest {
    public static final String ACCOUNT_SID = "AC583d503138def878fda30f5b0d7136dc";
    public static final String AUTH_TOKEN = "28fd720960a43b91d8f80c6c7909428d";


    public TwilioTest(String ACCOUNT_SID, String AUTH_TOKEN) { }

    public static String getAccountSid() {
        return ACCOUNT_SID;
    }

    public static String getAuthToken() {
        return AUTH_TOKEN;
    }

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+12102743221"),
                new PhoneNumber("15558675309"),
                "Hello from the other side").create();

    }
}