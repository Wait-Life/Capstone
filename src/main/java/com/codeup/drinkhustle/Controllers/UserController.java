package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Models.Event;
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

    public UserController(EventRepository eventDao, UserRepository users, PasswordEncoder passwordEncoder) {
        this.eventDao = eventDao;
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("client/register")
    public String viewClientRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/clientRegistration";
    }

    @PostMapping("client/register")
    public String registerClient(@ModelAttribute User user) {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            users.save(user);
            return "redirect:/";
    }

    @GetMapping("user/register")
    public String viewUserRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/bartenderRegistration";
    }

    @PostMapping("user/register")
    public String registerUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setIsClient(1);
        users.save(user);
        return "redirect:/";
    }

    @GetMapping("/users/account")
    public String showProfile(Model viewModel){
        User userSession= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Event> events = eventDao.findByUserId(userSession.getId());
        viewModel.addAttribute("events", events);
        viewModel.addAttribute("user", userSession);
        return "users/account";
    }

}
