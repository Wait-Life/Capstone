package Controllers;

import Repos.EventRepository;
import Repos.UserRepository;
import models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository users;

    public UserController(UserRepository users, EventRepository eventDao){
        this.users = users;
        this.eventDao = eventDao;
    }

    @GetMapping ("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveNewUser(@ModelAttribute User user){
        users.save(user);
        return "redirect:/login";
    }
}
