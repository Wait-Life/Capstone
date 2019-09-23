package com.codeup.drinkhustle.Controllers;

import com.codeup.drinkhustle.Repos.EventRepository;
import com.codeup.drinkhustle.Repos.UserRepository;
import com.codeup.drinkhustle.Models.Event;
import com.codeup.drinkhustle.Models.User;
import com.codeup.drinkhustle.Services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    private final EventRepository eventDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public EventController(EventRepository eventRepository, UserRepository userRepo, EmailService emailService){
        this.userDao = userRepo;
        this.eventDao = eventRepository;
        this.emailService = emailService;
    }

    @GetMapping("/events")
    public String index(Model vModel) {
        Iterable<Event> events = eventDao.findAll();
        vModel.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("/events/{id}")
    public String show(@PathVariable long id, Model viewModel) {
        Event event = eventDao.findOne(id);
        viewModel.addAttribute("event", event);
        return "events/show";
    }

    @GetMapping("/events/search")
    public String show(@RequestParam(name = "term") String term, Model viewModel) {
        List<Event> events = eventDao.searchByTitleLike(term);
        viewModel.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("/events/{id}/edit")
    public String edit(@PathVariable long id, Model viewModel) {
        Event event = eventDao.findOne(id);
        viewModel.addAttribute("event", event);
        return "events/edit";
    }

    @PostMapping("/events/{id}/edit")
    public String update(@PathVariable long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "startTime") String startTime,
                         @RequestParam(name = "endTime") String endTime,
                         @RequestParam(name = "description") String description,
                         Model viewModel) {
        Event eventToBeUpdated = eventDao.findOne(id);
        eventToBeUpdated.setTitle(title);
        eventToBeUpdated.setStartTime(startTime);
        eventToBeUpdated.setEndTime(endTime);
        eventToBeUpdated.setDescription(description);
        eventDao.save(eventToBeUpdated);
        return "redirect:/events/" + eventToBeUpdated.getId();
    }

    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable long id){
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
            @ModelAttribute Event eventPassedIn
    ) {
        User userDB = userDao.findOne(1L);
        eventPassedIn.setUser(userDB);
        Event savedEvent = eventDao.save(eventPassedIn);
//        emailService.prepareAndSend(
////                savedEvent,
////                "Event created",
////                String.format("Event with the id %d has been created", savedEvent.getId()));
        return "redirect:/events/" + savedEvent.getId();
    }
}
