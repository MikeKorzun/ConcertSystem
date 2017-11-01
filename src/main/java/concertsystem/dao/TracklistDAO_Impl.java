package concertsystem.dao;

import concertsystem.model.Song;
import concertsystem.model.Tracklist;
import concertsystem.model.User;
import concertsystem.service.SecurityService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public class TracklistDAO_Impl implements TracklistDAO {

    private SessionFactory sessionFactory;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserDAO userDAO;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //work
    @Override
    public void addTracklistSong(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tracklist tracklistSong = new Tracklist();
        Song song = songDAO.getSongByID(id);
        tracklistSong.setTracklistSongID(song.getId());
        tracklistSong.setTracklistSongArtist(song.getSongArtist());
        tracklistSong.setTracklistSongTitle(song.getSongTitle());
        tracklistSong.setTracklistSongVotes(song.getSongVote());
        // Set для проголосовавших User
        tracklistSong.setVotedUsers(new HashSet<>());
        song.setSongInTracklist(true);
        session.update(song);
        session.save(tracklistSong);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateTracklistSong(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(songDAO.getSongByID(id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteTracklistSong(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Song song = songDAO.getSongByID(id);
        if (song!=null) session.delete(song);
        session.getTransaction().commit();
        session.close();
    }
// select Track by SongID in Tracklist
    @Override
    public Tracklist getTracklistSongByID(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Tracklist where tracklistSongID = :id");
        query.setInteger("id", id);
        Tracklist tracklistSong = (Tracklist) query.uniqueResult();
        session.close();
        return tracklistSong;
    }
// work
    @Override
    public List<Tracklist> getTracklistList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Tracklist");
        List<Tracklist> songList = query.list();
        session.close();
        return songList;
    }
// work
    @Override
    public void addTracklistSongVote(int id, String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tracklist tracklistSong = getTracklistSongByID(id);
        tracklistSong.setTracklistSongVotes(tracklistSong.getTracklistSongVotes()+1);
        User user = userDAO.findByUsername(name);
        System.out.println("========="+"UserID = " + user.getId() + " UserName = " + user.getUsername()+"=========");
        // добавляем User-а в Set для проголосовавших User
        tracklistSong.getVotedUsers().add(user);
        session.update(tracklistSong);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void clearTracklist() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Tracklist> tracks = getTracklistList();
        // установка флага SongInTracklist = false и удаление Track-а
        for (Tracklist track: tracks) {
            Song song = songDAO.getSongByID(track.getTracklistSongID());
            song.setSongInTracklist(false);
            session.update(song);
            session.delete(track);
        }

        /*Query query = session.createSQLQuery("DELETE FROM concertsystemdb.tracklist");
        query.executeUpdate();*/
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String isUserVotedThisTrack(int id, String name) {
        String userHasVotedThisTrack = "false";
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tracklist votedTrack = getTracklistSongByID(id);
        User currentLoggedInUser = userDAO.findByUsername(name);
        for (User user:votedTrack.getVotedUsers()) {
            if ((user.getId()==currentLoggedInUser.getId())) {
                System.out.println("=====XXXXX===== UserID = "+currentLoggedInUser.getId()
                        +" UserName = "+currentLoggedInUser.getUsername()+" has voted for SongTitle = "
                        +votedTrack.getTracklistSongTitle()+"=====XXXXX=====");
                System.out.println("=====XXXXX=====TRUEEEEE=====XXXXX=====");
                userHasVotedThisTrack = "true";
            }
        }
        session.getTransaction().commit();
        session.close();
        System.out.println("=====XXXXX=====FALSEEE=====XXXXX=====");
        return userHasVotedThisTrack;
    }

    @Override
    public List<Tracklist> printSortedTrackList(int count) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Tracklist ORDER BY tracklistSongVotes DESC ");
        List<Tracklist> list = query.list();
        List<Tracklist> sortedList = new ArrayList<>();
        for (int i=0; i<count; i++) {
            sortedList.add(list.get(i));
        }
        session.close();
        return sortedList;
    }


}
