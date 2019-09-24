package com.codeup.drinkhustle.Models;

import com.codeup.drinkhustle.Services.DateTimeFormatter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Events")
public class Event {

    @Id @GeneratedValue
    private long id;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "DATETIME", name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(nullable = false, columnDefinition = "DATETIME", name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private long bartendersNeeded;

    @Column(nullable = false)
    private String description;

    @OneToOne
    private User clients;

    @ManyToOne
    private User users;


    public Event(long id, long ownerId, String title, Date startTime, Date endTime, String address, long bartendersNeeded, String description, User clients, User bartenders) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.bartendersNeeded = bartendersNeeded;
        this.description = description;
        this.clients = clients;
        this.users = bartenders;
    }


    public Event() {}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

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

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public void setUser(User userDB) { }

    public User getClients() {
        return clients;
    }

    public void setClients(User clients) {
        this.clients = clients;
    }
}
