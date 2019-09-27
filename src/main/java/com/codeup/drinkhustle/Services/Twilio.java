package com.codeup.drinkhustle.Services;
import com.codeup.drinkhustle.Models.User;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.ChatGrant;
import org.springframework.web.bind.annotation.ModelAttribute;

public class Twilio {

    public static void main(@ModelAttribute User user) {


        String twilioAccountSid =  "IS9c584cb9b18c4a6fb2674d6b1bb89088";
        String twilioApiKey = "SK93270ed8dfcc39fb0b0546a3b6469be1";
        String twilioApiSecret = "Ccby76RTXUrpDO0eKTHJuXYwmjtt1yYN";

        String serviceSid = "";
        String identity = user.getEmail();

        ChatGrant grant = new ChatGrant();
//        grant.getServiceSid(serviceSid);

        AccessToken token = new AccessToken.Builder(twilioAccountSid, twilioApiKey, twilioApiSecret)
                .identity(identity).grant(grant).build();

        System.out.println(token.toJwt());

    }
}
