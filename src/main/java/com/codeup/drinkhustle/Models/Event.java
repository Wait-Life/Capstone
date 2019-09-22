package com.codeup.drinkhustle.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Events")
public class Event {


    @Id @GeneratedValue
    private long id;

    @Column
    private long owner_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private long bartendersNeeded;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private User clients;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "event")
    private List<User> bartenders;


    public Event(long id, long owner_id, String title, Date start_time, Date end_time, String address, long bartendersNeeded, String description, User clients, List<User> bartenders) {
        this.id = id;
        this.owner_id = owner_id;
        this.title = title;
        this.start_time = start_time;
        this.end_time = end_time;
        this.address = address;
        this.bartendersNeeded = bartendersNeeded;
        this.description = description;
        this.clients = clients;
        this.bartenders = bartenders;
    }

    public Event (Event copy) {
        id = copy.id;
        owner_id = copy.owner_id;
        title = copy.title;
        start_time = copy.start_time;
        end_time = copy.end_time;
        address = copy.address;
        bartendersNeeded = copy.bartendersNeeded;
        description = copy.description;
        clients = copy.clients;
        bartenders = copy.bartenders;
    }

    public Event() {}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getOwner_id() { return owner_id; }

    public void setOwner_id(long owner_id) { this.owner_id = owner_id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Date getStart_time() { return start_time; }

    public void setStart_time(Date start_time) { this.start_time = start_time; }

    public Date getEnd_time() { return end_time; }

    public void setEnd_time(Date end_time) { this.end_time = end_time; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public long getBartendersNeeded() { return bartendersNeeded; }

    public void setBartendersNeeded(long bartendersNeeded) { this.bartendersNeeded = bartendersNeeded; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<User> getBartenders() { return bartenders; }

    public void setBartenders(List<User> bartenders) { this.bartenders = bartenders; }

    public void setUser(User userDB) { }

    public User getClients() {
        return clients;
    }

    public void setClients(User clients) {
        this.clients = clients;
    }
}
