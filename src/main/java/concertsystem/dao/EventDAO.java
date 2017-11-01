package concertsystem.dao;

import concertsystem.model.Event;
import concertsystem.model.Song;

import java.util.List;

public interface EventDAO {

    void addEvent(Event event);
    void deleteEvent(int id);
    Event getEventByName(String eventName);
    Event getEventByID(int id);
    List<Event> getEventsList();
    Event getCurrentActiveEvent();
    Song getVotingEventSong(int id);
    void stopEvent(int id);
     //    void fillEventTrackList(Event event);
//    List<Song> getEventTrackList();

}
