package concertsystem.service;

import concertsystem.dao.EventDAO;
import concertsystem.model.Event;
import concertsystem.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;

    @Autowired
    public void setEventDAO(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    @Transactional
    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }

    @Override
    @Transactional
    public void deleteEvent(int id) {
        eventDAO.deleteEvent(id);
    }

    @Override
    @Transactional
    public Event getEventByName(String eventName) {
        return eventDAO.getEventByName(eventName);
    }

    @Override
    @Transactional
    public Event getEventByID(int id) {
        return eventDAO.getEventByID(id);
    }

    @Override
    @Transactional
    public List<Event> getEventsList() {
        return eventDAO.getEventsList();
    }

    @Override
    @Transactional
    public Event getCurrentActiveEvent() {
        return eventDAO.getCurrentActiveEvent();
    }

    @Override
    @Transactional
    public Song getVotingEventSong(int id) {
        return eventDAO.getVotingEventSong(id);
    }

    @Override
    @Transactional
    public void stopEvent(int id) {
        eventDAO.stopEvent(id);
    }


}
