package concertsystem.controller;

import concertsystem.model.Event;
import concertsystem.model.User;

import concertsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private EventService eventService;
    @Autowired
    private TracklistService tracklistService;

    private final String ERROR_MESSAGE = "Неправильные имя/пароль";

    public void setTracklistService(TracklistService tracklistService) {
        this.tracklistService = tracklistService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String errors, String logout) {
        if (errors!=null) {
            model.addAttribute("error", "Username or Password is wrong");
        }
        if (logout!=null) {
            model.addAttribute("message", "You are logged out");
        }
        model.addAttribute("eventTracklist", tracklistService.getTracklistList());
        int currentEventID = eventService.getCurrentActiveEvent().getId();
        model.addAttribute("activeEvent", eventService.getEventByID(currentEventID));
        return "login";
    }

    @RequestMapping(value ="UsersList", method = RequestMethod.GET)
    public String songsList(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userList", userService.getUsersList());
        // тест метода findByUsername == working
        model.addAttribute("userUser", userService.findByUsername("user1"));
        return "UsersList";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "login";
    }

    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", ERROR_MESSAGE);
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    // Registration
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    // Registration
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.addUser(userForm);
        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());
        return "redirect:/SongsList";
    }


}
