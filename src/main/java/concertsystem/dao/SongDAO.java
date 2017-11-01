package concertsystem.dao;

import concertsystem.model.Song;
import java.util.List;

/**
 * DAO interface for Song
 */

public interface SongDAO {

    void addSong(Song song);
    void updateSong(Song song);
    void deleteSong(int id);
    List<Song> getSongByName(String songTitle);
    Song getSongByID(int id);
    List<Song> getSongList();
   // void addSongVote(Song song);

}
