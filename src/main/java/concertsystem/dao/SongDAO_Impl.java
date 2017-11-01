package concertsystem.dao;

import concertsystem.model.Song;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * DAO implementation for Song
 */

@Repository
public class SongDAO_Impl implements concertsystem.dao.SongDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addSong(Song song) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        song.setSongInTracklist(false);
        session.save(song);
        session.getTransaction().commit();
        session.close();
    }

    public void updateSong(Song song) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(song);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSong(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Song song = (Song) session.get(Song.class, id);
        if (song!=null) session.delete(song);
        session.getTransaction().commit();
        session.close();
    }

    public List<Song> getSongByName(String songTitle) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Song where songTitle = :songTitle");
        query.setString("songTitle", songTitle);
        List<Song> songList = query.list();
        session.close();
        return songList;
    }

    @Override
    public Song getSongByID(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Song where id = :id");
        query.setInteger("id", id);
        Song song = (Song) query.uniqueResult();
        session.close();
        return song;
    }

    public List<Song> getSongList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Song");
        List<Song> songList = query.list();
        session.close();
        return songList;
    }


}
