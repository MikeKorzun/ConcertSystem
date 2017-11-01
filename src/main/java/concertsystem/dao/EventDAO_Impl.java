package concertsystem.dao;

import concertsystem.model.Event;
import concertsystem.model.Tracklist;
import concertsystem.model.Song;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class EventDAO_Impl implements EventDAO {

    // количество случайно добавляемых песен в Tracklist при создании Event-а
    private int eventTrackListSize = 5;

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    TracklistDAO tracklistDAO;

    public void setTracklistDAO(TracklistDAO tracklistDAO) {
        this.tracklistDAO = tracklistDAO;
    }

    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addEvent(Event event) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Song> songList = songDAO.getSongList();
        Set<Song> eventTrackList = new HashSet<>();
        //очищаем Tracklist от предыдущего Event-а
        tracklistDAO.clearTracklist();
        // рандомно отбираем из общего списка песен песни для голосования
        while (eventTrackList.size()!=eventTrackListSize) {
            int id = (int)(Math.random()*songList.size());
            eventTrackList.add(songList.get(id));
            // заполняем Tracklist отобранными песнями для голосования
            tracklistDAO.addTracklistSong(songList.get(id).getId());
            // удаляем песню для исключения повтора
            songList.remove(id);
        }
        event.setEventSongsList(eventTrackList);
        event.setStatus("Active");
        session.save(event);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteEvent(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Event event = (Event) session.get(Event.class, id);
        if (event!=null) session.delete(event);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Event getEventByName(String eventName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Event where eventName = :eventName");
        query.setString("eventName", eventName);
        Event event = (Event) query.uniqueResult();
        session.close();
        return event;
    }

    @Override
    public Event getEventByID(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Event where id = :id");
        query.setInteger("id", id);
        Event event = (Event) query.uniqueResult();
        session.close();
        return event;
    }

    @Override
    public List<Event> getEventsList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Event");
        List<Event> eventList = query.list();
        session.close();
        return eventList;
    }

    // получаем текущее событие/голосование (по последнему id)
    @Override
    public Event getCurrentActiveEvent() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Event currentActiveEvent = new Event();
        Query query = session.createQuery("FROM Event ORDER BY id DESC ");
        List<Event> list = query.list();
        currentActiveEvent = list.get(0);
        session.close();
        return currentActiveEvent;
    }


    @Override
    public Song getVotingEventSong(int id) {
        Song votingEventSong = new Song();
        Set<Song> tracklist = getCurrentActiveEvent().getEventSongsList();
        for (Song song:tracklist) {
            if (song.getId()==id) votingEventSong = song;
        }
        return votingEventSong;
    }

    // заканчиваем событие/голосование
    @Override
    public void stopEvent(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Event currentEvent = getEventByID(id);
        currentEvent.setStatus("Finished");
        session.saveOrUpdate(currentEvent);
        session.getTransaction().commit();
        session.close();
    }



}
