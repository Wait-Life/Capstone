package com.codeup.drinkhustle.Services;

import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Repos.UserRepository;

public class SmsSender {
    public static final String ACCOUNT_SID = "AC583d503138def878fda30f5b0d7136dc";
    public static final String AUTH_TOKEN = "28fd720960a43b91d8f80c6c7909428d";
    private final UserDetailsLoader userDetailsLoader;
    private final UserRepository users;
    public User user;

    public SmsSender() {

    }
}
