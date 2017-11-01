package concertsystem.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity для песни
 */

@Entity
@Table(name = "songs")
public class Song {

    private int id;
    @NotBlank
    private String songArtist;
    @NotBlank
    private String songTitle;
    private Set<Event> eventsList = new HashSet<>();
    private int songVote;
    private boolean isSongInTracklist;

    public Song() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "SONG_ARTIST")
    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    @Column(name = "SONG_TITLE")
    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "event_song",
            joinColumns = @JoinColumn(name = "SONG_ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    public Set<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(Set<Event> eventsList) {
        this.eventsList = eventsList;
    }

    @Column(name = "SONG_VOTE")
    public int getSongVote() {
        return songVote;
    }

    public void setSongVote(int songVote) {
        this.songVote = songVote;
    }

    @Column(name = "SONG_ISINTRACKLIST")
    public boolean isSongInTracklist() {
        return isSongInTracklist;
    }

    public void setSongInTracklist(boolean songInTracklist) {
        isSongInTracklist = songInTracklist;
    }
}
