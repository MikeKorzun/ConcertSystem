package concertsystem.model;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/*
* Entity для события/голосования
 */

@Entity
@Table(name = "events", catalog = "concertsystemdb")
public class Event {

    private int id;
    @NotNull
    @NotBlank
    private String eventName;
    private String status;
    private Set<Song> eventSongsList = new HashSet<>();
    @NotNull
    @Min(5)
    private Integer ratingSize;

    public Event() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "EVENT_NAME")
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @ManyToMany
    @JoinTable(name = "event_song",
            joinColumns = @JoinColumn(name = "EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "SONG_ID"))
    public Set<Song> getEventSongsList() {
        return eventSongsList;
    }

    public void setEventSongsList(Set<Song> eventSongsList) {
        this.eventSongsList = eventSongsList;
    }

    @Column(name = "EVENT_STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "EVENT_RATINGSIZE")
    public Integer getRatingSize() {
        return ratingSize;
    }

    public void setRatingSize(Integer ratingSize) {
        this.ratingSize = ratingSize;
    }
}
