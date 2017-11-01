package concertsystem.controller;



import concertsystem.model.Song;
import concertsystem.model.User;
import concertsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SongController {

    private SongService songService;
    private EventService eventService;
    private TracklistService tracklistService;

    @Autowired
    public void setTracklistService(TracklistService tracklistService) {
        this.tracklistService = tracklistService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    @Qualifier(value = "songService")
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(value ="/SongsList", method = RequestMethod.GET)
    public String songsList(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("songsList", songService.getSongList());
        model.addAttribute("tracklistSongList", tracklistService.getTracklistList());
        //int currentEventID = eventService.getCurrentActiveEvent().getId();
        model.addAttribute("activeEvent", eventService.getCurrentActiveEvent());
        return "SongsList";
    }

    @RequestMapping(value = "/SongsList/add", method = RequestMethod.POST)
    public String addSong(@ModelAttribute("song") @Valid Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "SongsList";
        }
        if (song.getId()==0) {
            this.songService.addSong(song);
        } else {
            this.songService.updateSong(song);}
        return "redirect:/SongsList";
    }

    @RequestMapping("/delete/{id}")
    public String deleteSong(@PathVariable("id") int id) {
        songService.deleteSong(id);
        return "redirect:/SongsList";
    }

    @RequestMapping("/edit/{id}")
    public String editSong(@PathVariable("id") int id, Model model) {
        model.addAttribute("song", songService.getSongByID(id));
        model.addAttribute("songsList", songService.getSongList());
        return "SongsList";
    }

    @RequestMapping("SongData/{id}")
    public String songData(@PathVariable("id") int id, Model model) {
        model.addAttribute("song", songService.getSongByID(id));
        return "SongData";
    }

    // User голосует за Track только 1 раз. Как убрать кнопку после нажатия???
    @RequestMapping("/vote/{tracklistSongID}")
    public String voteSong(@PathVariable("tracklistSongID") int id, Model model) {
        if (tracklistService.isUserVotedThisTrack(id, getPrincipal()).equals("false")) {
            tracklistService.addTracklistSongVote(id, getPrincipal());
        }
        return "redirect:/SongsList";
    }

    // Добавить Song в EventTracklist залогиненым User-ом
    @RequestMapping("/addToTracklist/{id}")
    public String addSongToTracklist(@PathVariable("id") int id, Model model) {
        tracklistService.addTracklistSong(id);

        return "redirect:/SongsList";
    }

    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


}
