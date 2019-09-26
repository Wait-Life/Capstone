package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Repos.EventRepository;
import com.codeup.drinkhustle.Repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/register")
    public String viewClientRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("users/register")
    public String registerClient(@ModelAttribute User user) {
            String hash = passwordEncoder.encode(user.getPassword());
            user.setPassword(hash);
            users.save(user);
            return "redirect:/";
    }
//
//    @GetMapping("/register")
//    public String viewUserRegister(Model model) {
//        model.addAttribute("user", new User());
//        return "users/register";
//    }
//
//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute User user) {
//        try {
//            String hash = passwordEncoder.encode(user.getPassword());
//            user.setPassword(hash);
//            user.setIsClient(1);
//            users.save(user);
//            return "redirect:/";
//        } catch (InternalError ex) {
//            return null;
//        }
//    }

    //SHOW BARTENDER PROFILE
    @GetMapping("users/profile")
    public String showBartenderProfile(Model viewModel){
        User userSession= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("user", userSession);
        return "users/bartenderProfile";
    }

//    SHOW CLIENT PROFILE
    @GetMapping("client/profile")
    public String showClientProfile(Model vModel){
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Event> userEvents = eventDao.findByOwner(userSession);
        vModel.addAttribute("events", userEvents);
        vModel.addAttribute("user", userSession);
        return "users/clientProfile";
    }

//    VIEW ALL BARTENDERS

    @GetMapping("users/bartenders")
    public String viewAllProfiles(Model viewModel){
        Iterable<User> bartenders = users.findAll();
        viewModel.addAttribute("user", bartenders);
        return "users/viewBartenders";
    }


    //    VIEW INDIVIDUAL USER PROFILE
    @GetMapping("users/{id}/profile")
    public String show(@PathVariable long id, Model viewModel) {
        User user = userDao.findOne(id);
        viewModel.addAttribute("user", user);
        return "users/view";
    }

    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }
    @GetMapping("/users/search")
    public String show(@RequestParam(name = "userterm") String userterm, Model viewModel) {
        List<User> users = userDao.searchByNameLike(userterm);
        viewModel.addAttribute("users", users);
        return "users/index";
    }

}
