package com.codeup.drinkhustle.Models;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "events")
public class Event {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, name = "start_time")
    private Date startTime;

    @Column(nullable = false, name = "end_time")
    private Date endTime;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private long bartendersNeeded;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "events_bartenders",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> bartenders;

    public Event(long id, String title, Date date, Date startTime, Date endTime, String address, long bartendersNeeded, String description, User owner, List<User> bartenders) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.bartendersNeeded = bartendersNeeded;
        this.description = description;
        this.owner = owner;
        this.bartenders = bartenders;
    }


    public Event() {}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public Date getStartTime() { return startTime; }

    public void setStartTime(Date startTime) { this.startTime = startTime; }

    public Date getEndTime() { return endTime; }

    public void setEndTime(Date endTime) { this.endTime = endTime; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public long getBartendersNeeded() { return bartendersNeeded; }

    public void setBartendersNeeded(long bartendersNeeded) { this.bartendersNeeded = bartendersNeeded; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

    public List<User> getBartenders() { return bartenders; }

    public void setBartenders(List<User> bartenders) { this.bartenders = bartenders; }

    public void addBartender(User bartender) { this.bartenders.add(bartender); }

    public void removeBartender(User bartender) { this.bartenders.remove(bartender); }

}
