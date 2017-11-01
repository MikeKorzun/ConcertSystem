package concertsystem.model;


import javax.persistence.*;
import java.util.Set;

/*
 * Entity для списка песен в текущем голосовании
 */

@Entity
@Table(name = "tracklist", catalog = "concertsystemdb")
public class Tracklist {

    private int id;
    private int tracklistSongID;
    private String tracklistSongArtist;
    private String tracklistSongTitle;
    private int tracklistSongVotes;
    private Set<User> votedUsers;

    public Tracklist() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tracklist_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "tracklist_songID")
    public int getTracklistSongID() {
        return tracklistSongID;
    }

    public void setTracklistSongID(int tracklistSongID) {
        this.tracklistSongID = tracklistSongID;
    }

    @Column(name = "tracklist_songArtist")
    public String getTracklistSongArtist() {
        return tracklistSongArtist;
    }

    public void setTracklistSongArtist(String tracklistSongArtist) {
        this.tracklistSongArtist = tracklistSongArtist;
    }

    @Column(name = "tracklist_songTitle")
    public String getTracklistSongTitle() {
        return tracklistSongTitle;
    }

    public void setTracklistSongTitle(String tracklistSongTitle) {
        this.tracklistSongTitle = tracklistSongTitle;
    }

    @Column(name = "tracklist_SongVotes")
    public int getTracklistSongVotes() {
        return tracklistSongVotes;
    }

    public void setTracklistSongVotes(int tracklistSongVotes) {
        this.tracklistSongVotes = tracklistSongVotes;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_track",
               joinColumns = @JoinColumn(name = "tracklist_id"),
               inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    public Set<User> getVotedUsers() {
        return votedUsers;
    }

    public void setVotedUsers(Set<User> votedUsers) {
        this.votedUsers = votedUsers;
    }
}
