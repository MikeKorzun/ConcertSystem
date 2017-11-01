package concertsystem.dao;

import concertsystem.model.Song;
import concertsystem.model.Tracklist;

import java.util.List;

public interface TracklistDAO {

    void addTracklistSong(int id);
    void updateTracklistSong(int id);
    void deleteTracklistSong(int id);
    Tracklist getTracklistSongByID(int id);
    List<Tracklist> getTracklistList();
    void addTracklistSongVote(int id, String name);
    void clearTracklist();
    String isUserVotedThisTrack(int id, String name);
    List<Tracklist> printSortedTrackList(int count);
}
