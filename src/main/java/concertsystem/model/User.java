package concertsystem.model;

import javax.persistence.*;


import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Entity для пользователя
 */

@Entity
@Table(name = "users")
public class User {

    private int id;
    private String username;
    private String password;
    private String confirmPassword;
    private String role;
    private Set<Tracklist> votedTracks;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "USER_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "USER_USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Column(name = "USER_ROLE")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable(name = "user_track",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "tracklist_id"))
    public Set<Tracklist> getVotedTracks() {
        return votedTracks;
    }

    public void setVotedTracks(Set<Tracklist> votedTracks) {
        this.votedTracks = votedTracks;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
