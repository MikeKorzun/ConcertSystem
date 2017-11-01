package concertsystem.service;

import concertsystem.dao.TracklistDAO;
import concertsystem.model.Song;
import concertsystem.model.Tracklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TracklistServiceImpl implements TracklistService{

    @Autowired
    private TracklistDAO tracklistDAO;

    public void setTracklistDAO(TracklistDAO tracklistDAO) {
        this.tracklistDAO = tracklistDAO;
    }


    @Override
    @Transactional
    public void addTracklistSong(int id) {
        tracklistDAO.addTracklistSong(id);
    }

    @Override
    @Transactional
    public void updateTracklistSong(int id) {
        tracklistDAO.updateTracklistSong(id);
    }

    @Override
    @Transactional
    public void deleteTracklistSong(int id) {

    }

    @Override
    @Transactional
    public Tracklist getTracklistSongByID(int id) {
        return tracklistDAO.getTracklistSongByID(id);
    }

    @Override
    @Transactional
    public List<Tracklist> getTracklistList() {
        return tracklistDAO.getTracklistList();
    }

    @Override
    @Transactional
    public void addTracklistSongVote(int id, String name) {
        tracklistDAO.addTracklistSongVote(id, name);
    }

    @Override
    @Transactional
    public void clearTracklist() {
        tracklistDAO.clearTracklist();
    }

    @Override
    @Transactional
    public String isUserVotedThisTrack(int id, String name) {
        return tracklistDAO.isUserVotedThisTrack(id, name);
    }

    @Override
    @Transactional
    public List<Tracklist> printSortedTrackList(int count) {
        return tracklistDAO.printSortedTrackList(count);
    }


}
