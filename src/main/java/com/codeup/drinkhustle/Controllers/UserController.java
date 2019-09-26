package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Repos.EventRepository;
import com.codeup.drinkhustle.Repos.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private EventRepository eventDao;
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(EventRepository eventDao, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.eventDao = eventDao;
        this.userDao = userDao;
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
            userDao.save(user);
            return "redirect:/";
    }

    @GetMapping("users/register")
    public String viewUserRegister(Model model) {
        model.addAttribute("user", new User());
        return "users/bartenderRegistration";
    }

    @PostMapping("users/register")
//    public String registerUser(@RequestParam(name = "file") String fileUR, @ModelAttribute User user) {
    public String registerUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setIsClient(1);
//        user.setPhotoUrl(fileUR);
        userDao.save(user);
        return "redirect:/";
    }

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


//    EDIT CLIENTS
    @GetMapping("client/profile/{id}/edit")
    public String editClientProfile(@PathVariable long id, Model viewModel) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("user", userSession);
        return ("users/editClientProfile");
    }

    @PostMapping("/client/profile/{id}/edit")
    public String editClientProfile(@PathVariable long id,
                                    @ModelAttribute User user,
                                    @RequestParam(name="email") String email,
                                    @RequestParam(name="name") String name,
                                    @RequestParam(name="company") String company){
        User updateUser = userDao.findOne(id);
        String hash = passwordEncoder.encode(user.getPassword());
        updateUser.setPassword(hash);
        updateUser.setEmail(email);
        updateUser.setName(name);
        updateUser.setCompany(company);
        userDao.save(updateUser);
        return ("redirect:/login?/logout");
    }

    //    EDIT BARTENDERS
    @GetMapping("users/profile/{id}/edit")
    public String editBartenderForm(@PathVariable long id, Model viewModel) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("user", userSession);
        return ("users/editBartenderProfile");
    }

    @PostMapping("/users/profile/{id}/edit")
    public String editBartenderProfile(@PathVariable long id,
                                       @ModelAttribute User user,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "tabcCert") String tabcCert,
                                       @RequestParam(name = "foodCert") String foodCert,
                                       Model viewModel) {
        User updateUser = userDao.findOne(id);
        String hash = passwordEncoder.encode(user.getPassword());
        updateUser.setPassword(hash);
        updateUser.setEmail(email);
        updateUser.setName(name);
        updateUser.setTabcCert(tabcCert);
        updateUser.setFoodCert(foodCert);
        userDao.save(updateUser);
        return "redirect:/login?/logout";
    }

//    VIEW ALL BARTENDERS

    @GetMapping("users/viewAll")
    public String viewAllProfiles(Model viewModel){
        Iterable<User> users = userDao.findAll();
        viewModel.addAttribute("user", users);
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
}
