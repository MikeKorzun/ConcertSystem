package concertsystem.controller;

import concertsystem.model.Event;
import concertsystem.service.EventService;
import concertsystem.service.SongService;
import concertsystem.service.TracklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private SongService songService;
    @Autowired
    TracklistService tracklistService;

    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setTracklistService(TracklistService tracklistService) {
        this.tracklistService = tracklistService;
    }

    @RequestMapping(value = "/Admin", method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("eventList", eventService.getEventsList());
        int currentEventID = eventService.getCurrentActiveEvent().getId();
        model.addAttribute("activeEvent", eventService.getEventByID(currentEventID));
        return "Admin";
    }

    @RequestMapping(value = "/Admin/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute("event") @Valid Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "Admin";
        }
        eventService.addEvent(event);
        return "redirect:/Admin";
    }

    @RequestMapping("EventData/{id}")
    public String eventData(@PathVariable("id") int id, Model model) {
        model.addAttribute("event", eventService.getEventByID(id));
        model.addAttribute("eventTracklist", tracklistService.getTracklistList());
        return "EventData";
    }

    @RequestMapping("/stop/{id}")
    public String stopEvent(@PathVariable("id") int id, Model model) {
        eventService.stopEvent(id);
        return "redirect:/Admin";
    }

    @RequestMapping("/ratingtracklist")
    public String printRating(Model model) {
        //int currentEventID = eventService.getCurrentActiveEvent().getId();
        model.addAttribute("activeEvent", eventService.getCurrentActiveEvent());
        int ratingSize = eventService.getCurrentActiveEvent().getRatingSize();
        if (tracklistService.printSortedTrackList(ratingSize)!=null) {
            model.addAttribute("sortedTrackList", tracklistService.printSortedTrackList(ratingSize));
        }
        return "ratingtracklist";
    }

}
