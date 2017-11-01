package concertsystem.service;

import concertsystem.model.Event;
import concertsystem.model.Song;

import java.util.List;

public interface EventService {

    void addEvent(Event event);
    void deleteEvent(int id);
    Event getEventByName(String eventName);
    Event getEventByID(int id);
    List<Event> getEventsList();
    Event getCurrentActiveEvent();
    Song getVotingEventSong(int id);
    void stopEvent(int id);
    // List<Song> getEventSongsList();

}
