package models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Events")
public class Event {

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<User> users;

    @Id @GeneratedValue
    private long id;

    @Column
    private long owner_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String start_time;

    @Column(nullable = false)
    private String end_time;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private long bartendersNeeded;

    @Column(nullable = false)
    private String description;



    public Event(long id, long owner_id, String title, String start_time, String end_time, String address, long bartendersNeeded, String description) {
        this.id = id;
        this.owner_id = owner_id;
        this.title = title;
        this.start_time = start_time;
        this.end_time = end_time;
        this.address = address;
        this.bartendersNeeded = bartendersNeeded;
        this.description = description;
    }

    public Event() {}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getOwner_id() { return owner_id; }

    public void setOwner_id(long owner_id) { this.owner_id = owner_id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getStart_time() { return start_time; }

    public void setStart_time(String start_time) { this.start_time = start_time; }

    public String getEnd_time() { return end_time; }

    public void setEnd_time(String end_time) { this.end_time = end_time; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public long getBartendersNeeded() { return bartendersNeeded; }

    public void setBartendersNeeded(long bartendersNeeded) { this.bartendersNeeded = bartendersNeeded; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<User> getUsers() { return users; }

    public void setUsers(List<User> users) { this.users = users; }

    public void setUser(User userDB) {
    }
}
