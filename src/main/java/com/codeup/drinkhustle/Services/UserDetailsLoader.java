package com.codeup.drinkhustle.Services;

import com.codeup.drinkhustle.Controllers.UserWithRoles;
import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Repos.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService{
    private final UserRepository users;

    public UserDetailsLoader(UserRepository users) {
        this.users = users;
    }

        @Override
        public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
            User user = users.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found for " + email);
            }
            return new UserWithRoles(user);
        }
    }
