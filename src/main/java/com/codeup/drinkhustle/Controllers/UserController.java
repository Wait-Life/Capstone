
package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Repos.EventRepository;
import com.codeup.drinkhustle.Repos.UserRepository;
import com.codeup.drinkhustle.Services.SmsSender;
import com.twilio.Twilio;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.List;

@Controller
public class UserController {
    private EventRepository eventDao;
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private TwilioTest twilioTest;
    private PhoneNumber originPhoneNumber = new PhoneNumber("+12815576961");

    public UserController(EventRepository eventDao, UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.eventDao = eventDao;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    TwilioTest twilio = new TwilioTest(TwilioTest.ACCOUNT_SID, TwilioTest.AUTH_TOKEN);



//
//    @GetMapping("/register")
//    public String viewClientRegister(Model model) {
//        model.addAttribute("user", new User());
//        return "users/register";
//    }

    @GetMapping("hustlers/register")
    public String viewBartenderRegistration(Model model) {
        model.addAttribute("user", new User());
        return "users/bartenderRegister";
    }

    @PostMapping("hustlers/register")
    public String registerUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setIsClient(1);
        userDao.save(user);
        try {
            Twilio.init(TwilioTest.getAccountSid(), TwilioTest.getAuthToken());
            Message message = Message.creator(new PhoneNumber("+1" + user.getPhoneNum()), originPhoneNumber, "Thanks for signing up " + user.getName() + "!").create();
            message.getSid();
        } catch (Exception e) {
            System.out.println("Something went wrong with Twilio texting");
        }
        return "redirect:/";
    }

    @GetMapping("clients/register")
    public String viewClientRegistration(Model model) {
        model.addAttribute("user", new User());
        return "users/clientRegister";
    }

    @PostMapping("clients/register")
    public String registerClient(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setIsClient(0);
        userDao.save(user);
        try {
            Twilio.init(TwilioTest.getAccountSid(), TwilioTest.getAuthToken());
            Message message = Message.creator(new PhoneNumber("+1" + user.getPhoneNum()), originPhoneNumber, "Thanks for signing up " + user.getName() + "!").create();
            message.getSid();
        } catch (Exception e) {
            System.out.println("Something went wrong with Twilio texting");
        }
        return "redirect:/";
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
                                    @RequestParam(name="company") String company,
                                    @RequestParam(name = "phoneNum") String phoneNum){
        User updateUser = userDao.findOne(id);
        updateUser.setEmail(email);
        updateUser.setName(name);
        updateUser.setCompany(company);
        updateUser.setPhoneNum(phoneNum);
        userDao.save(updateUser);
        return ("redirect:/client/profile/");
    }

    //    EDIT BARTENDERS
    @GetMapping("hustlers/profile/{id}/edit")
    public String editBartenderForm(@PathVariable long id, Model viewModel) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        viewModel.addAttribute("user", userSession);
        return ("users/editBartenderProfile");
    }

    @PostMapping("/hustlers/profile/{id}/edit")
    public String editBartenderProfile(@PathVariable long id,
                                       @ModelAttribute User user,
                                       @RequestParam(name = "email") String email,
                                       @RequestParam(name = "name") String name,
                                       @RequestParam(name = "tabcCert") String tabcCert,
                                       @RequestParam(name = "foodCert") String foodCert,
                                       @RequestParam(name = "phoneNum") String phoneNum,
                                       Model viewModel) {
        User updateUser = userDao.findOne(id);
        updateUser.setEmail(email);
        updateUser.setName(name);
        updateUser.setTabcCert(tabcCert);
        updateUser.setFoodCert(foodCert);
        updateUser.setPhoneNum(phoneNum);
        userDao.save(updateUser);
        return "redirect:/hustlers/profile/";
    }

    //SHOW BARTENDER PROFILE
    @GetMapping("hustlers/profile")
    public String showBartenderProfile(Model viewModel){
        User userSession= userDao.findOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        viewModel.addAttribute("user", userSession);
        return "users/bartenderProfile";
    }

    //    SHOW CLIENT PROFILE
    @GetMapping("client/profile")
    public String showClientProfile(Model vModel){
        User userSession = userDao.findOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        Iterable<Event> userEvents = eventDao.findByOwner(userSession);
        vModel.addAttribute("events", userEvents);
        vModel.addAttribute("user", userSession);
        return "users/clientProfile";
    }

//    VIEW ALL BARTENDERS

    @GetMapping("hustlers/bartenders")
    public String viewAllProfiles(Model viewModel){
        Iterable<User> bartenders = userDao.findAll();
        viewModel.addAttribute("user", bartenders);
        return "users/viewBartenders";
    }

    //    VIEW INDIVIDUAL USER PROFILE
    @GetMapping("hustlers/{id}/profile")
    public String show(@PathVariable long id, Model viewModel) {
        User user = userDao.findOne(id);
        viewModel.addAttribute("user", user);
        return "users/view";
    }

    @GetMapping("/hustlers/search")
    public String show(@RequestParam(name = "userterm") String userterm, Model viewModel) {
        List<User> users = userDao.searchByNameLike(userterm);
        viewModel.addAttribute("users", users);
        return "users/index";
    }

}