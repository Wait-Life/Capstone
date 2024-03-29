package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Repos.EventRepository;
import com.codeup.drinkhustle.Repos.UserRepository;
import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Services.EmailService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public EventController(EventRepository eventRepository, UserRepository userRepo, EmailService emailService) {
        this.userDao = userRepo;
        this.eventDao = eventRepository;
        this.emailService = emailService;
    }

    TwilioTest twilioTest = new TwilioTest();
    @Value("${twilio-acct-sid}")
    private String twilioSid;
    @Value("${twilio-auth-token}")
    private String twilioToken;
    @Value("${origin-phone-number}")
    private PhoneNumber originPhoneNumber;



    @GetMapping("/events")
    public String index(Model vModel) {
        Iterable<Event> events = eventDao.findAll();
        vModel.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("/events/{id}")
    public String showIndividualEvent(@PathVariable long id, Model viewModel) {
        Event event = eventDao.findOne(id);
        viewModel.addAttribute("event", event);
        return "events/show";
    }

    @GetMapping("/events/location")
    public String showloc(@RequestParam(name = "termloc") String termloc, Model viewModel) {
        List<Event> eventsA = eventDao.searchByAddressLike(termloc);
        viewModel.addAttribute("events", eventsA);
        return "events/index";
    }

    @GetMapping("/events/search")
    public String show(@RequestParam(name = "term") String term, Model viewModel) {
        List<Event> events = eventDao.searchByTitleLike(term);
        List<Event> eventsD = eventDao.searchByDescriptionLike(term);
        viewModel.addAttribute("events", eventsD);
        viewModel.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("/events/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        Event event = eventDao.findOne(id);
        System.out.println(event);
        viewModel.addAttribute("event", event);
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String update(@PathVariable long id,
                         @RequestParam(name = "title") String title,
//                         @RequestParam(name = "startTime") String startTime,
//                         @RequestParam(name = "endTime") String endTime,
                         @RequestParam(name = "bartendersNeeded") Long bartendersNeeded,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "address") String address,
                         Model viewModel) throws ParseException {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd'T'hh:mm");
//        Date startDate = dateFormat.parse(startTime);
//        Date endDate = dateFormat.parse(endTime);
        Event eventToBeUpdated = eventDao.findOne(id);
        eventToBeUpdated.setTitle(title);
//        eventToBeUpdated.setStartTime(startTime);
//        eventToBeUpdated.setEndTime(endTime);
        eventToBeUpdated.setDescription(description);
        eventToBeUpdated.setAddress(address);
        eventToBeUpdated.setBartendersNeeded(bartendersNeeded);
        eventDao.save(eventToBeUpdated);
        return "redirect:/events/" + eventToBeUpdated.getId();
    }

//    @GetMapping("/events/{id}/addBartenders")
//    public String addBartener(@PathVariable long id, Model viewModel) {
//        Event event = eventDao.findOne(id);
//        viewModel.addAttribute("event", event);
//        return "events/";
//    }
//
//    @PostMapping("/events/{id}/addBartender")
//    public String update(@PathVariable long id,
//                         @RequestParam(name = "bartendersNeeded") Long bartendersNeeded,
//                         Model viewModel) throws ParseException {
//        Event eventToBeUpdated = eventDao.findOne(id);
//        eventToBeUpdated.setBartendersNeeded(bartendersNeeded - 1);
//        eventDao.save(eventToBeUpdated);
//        return "redirect:/events/" + eventToBeUpdated.getId();
//    }

    @PostMapping("/events/{id}/delete")
    public String deleteEvent(@PathVariable long id) {
        Event event = eventDao.findOne(id);
        List<User> bartenders = new ArrayList<>();
        event.setBartenders(bartenders);
        eventDao.delete(id);
        return "redirect:/events";
    }

    @GetMapping("/events/create")
    public String showCreateForm(Model model) {
        model.addAttribute("event", new Event());
        return "events/eventRegistration";
    }

    @PostMapping("/events/create")
    public String createEvent(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "date") String date,
            @RequestParam(name = "startTime") String startTime,
            @RequestParam(name = "endTime") String endTime,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "bartendersNeeded") int bartendersNeeded,
            @RequestParam(name = "description") String description,
            Model viewModel) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("h:mm");
        Date startDate = dateFormat.parse(date);
        Date newStartTime = timeFormat.parse(startTime);
        Date newEndTime = timeFormat.parse(endTime);
        Event eventToBeCreated = new Event();
        eventToBeCreated.setTitle(title);
        eventToBeCreated.setDate(startDate);
        eventToBeCreated.setStartTime(newStartTime);
        eventToBeCreated.setEndTime(newEndTime);
        eventToBeCreated.setAddress(address);
        eventToBeCreated.setBartendersNeeded(bartendersNeeded);
        eventToBeCreated.setDescription(description);
        User userDB = userDao.findOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        eventToBeCreated.setOwner(userDB);
        Event savedEvent = eventDao.save(eventToBeCreated);
//        emailService.prepareAndSend(
////                savedEvent,
////                "Event created",
////                String.format("Event with the id %d has been created", savedEvent.getId()));
        return "redirect:/client/profile";
//        return "redirect:/events/" + savedEvent.getId();
    }


    //    Add a bartender to an event
    @GetMapping("/events/request/{id}")
    public String addBartenderToEvent(@PathVariable long id, Model vModel) {
        User user = userDao.findOne(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        vModel.addAttribute("user", user);
        Event event = eventDao.findOne(id);
        System.out.println("Hey this code ran");
        event.addBartender(user);
        eventDao.save(event);
        try {
            Twilio.init(twilioSid, twilioToken);
            Message message = Message.creator(new PhoneNumber("1" + event.getOwner().getPhoneNum()), originPhoneNumber, "Someone has expressed interest in your event! Log in to your DrinkHustle account to see who.").create();
            message.getSid();
        } catch (Exception e) {
            System.out.println("Something went wrong with Twilio!");
        }
        return "redirect:/events/";
    }


//    Deny bartender from event
    @PostMapping("/events/appliedbartenders/{id}")
    public String removeAppliedBartenderFromEvent(@PathVariable long id, Model vModel, @RequestParam(name="deniedBartender") long dbId ) {
        Event event = eventDao.findOne(id);
        List <User> acceptedBartenders = new ArrayList<>();
        List <User> appliedBartenders = event.getBartenders();
        for (User bartender : appliedBartenders) {
            if (bartender.getId() != dbId){
                acceptedBartenders.add(bartender);
            }
        }
        event.setBartenders(acceptedBartenders);
        eventDao.save(event);
        return "redirect:/events/appliedbartenders/" + id;
    }

//    @PostMapping("/events/{id}/addBartender")
//    public String addAppliedBartenderToEvent(@PathVariable long id, Model model,
//                        @RequestParam(name = "bartendersNeeded") int bartendersNeeded,
//                        @RequestParam(name = "bartenderId") long bartenderId) {
//
//        Event eventToBeUpdated = eventDao.findOne(id);
//        eventToBeUpdated.setBartendersNeeded(bartendersNeeded - 1);
//        System.out.println(bartendersNeeded);
//        User user = userDao.findOne(bartenderId);
//        eventDao.save(eventToBeUpdated);
//        Twilio.init(twilioSid, twilioToken);
//        Message message = Message.creator(new PhoneNumber(user.getPhoneNum()), originPhoneNumber, "Your request to work a Drink Hustle event has been approved!").create();
//        message.getSid();
//        return "redirect:/events/appliedbartenders/" + id;
//    }


//    Populate bartenders to account
    @GetMapping("events/appliedbartenders/{id}")
    public String showAppliedBartenders(@PathVariable long id, Model vModel) {
        Event event = eventDao.findOne(id);
        Iterable<User> bartenders = userDao.findAll();
        vModel.addAttribute("event", event);
        System.out.println(bartenders);
        return "events/appliedBartenders";
    }
}