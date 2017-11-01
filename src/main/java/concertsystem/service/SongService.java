package concertsystem.service;

import concertsystem.model.Song;
import java.util.List;

/**
 * Service interface for Song
 */

public interface SongService {

    void addSong(Song song);
    void updateSong(Song song);
    void deleteSong(int id);
    List<Song> getSongByName(String name);
    Song getSongByID(int id);
    List<Song> getSongList();
   // void addSongVote(Song song);
}
