package concertsystem.service;

import concertsystem.dao.SongDAO;
import concertsystem.model.Song;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongServiceImpl implements SongService{

    private SongDAO songDAO;

    public void setSongDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    @Transactional
    public void addSong(Song song) {
        this.songDAO.addSong(song);
    }

    @Transactional
    public void updateSong(Song song) {
        songDAO.updateSong(song);
    }

    @Transactional
    public void deleteSong(int id) {
        songDAO.deleteSong(id);
    }

    @Transactional
    public List<Song> getSongByName(String songTitle) {
        return songDAO.getSongByName(songTitle);
    }

    @Transactional
    public Song getSongByID(int id) {
        return songDAO.getSongByID(id);
    }

    @Transactional
    public List<Song> getSongList() {
        return songDAO.getSongList();
    }

    /*@Override
    @Transactional
    public void addSongVote(Song song) {
        songDAO.addSongVote(song);
    }*/
}
