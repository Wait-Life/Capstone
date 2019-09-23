package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Repos.EventRepository;
import com.codeup.drinkhustle.Repos.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private EventRepository eventDao;
    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String viewUserRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/clientRegistration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            users.save(user);
            return "redirect:/eventRegistration";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

}
