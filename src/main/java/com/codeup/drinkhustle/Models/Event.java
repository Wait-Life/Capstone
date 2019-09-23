package com.codeup.drinkhustle.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Events")
public class Event {


    @Id
    @GeneratedValue
    private long id;

    @Column(name = "owner_id")
    private long ownerId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "DATETIME", name = "start_time")
//    @Temporal(TemporalType.TIMESTAMP)
    private String startTime;

    @Column(nullable = false, columnDefinition = "DATETIME", name = "end_time")
//    @Temporal(TemporalType.TIMESTAMP)
    private String endTime;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private long bartendersNeeded;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "events")
    private List<User> users;



    public Event(long id, long owner_id, String title, Date start_time, Date end_time, String address, long bartendersNeeded, String description, List<User> bartenders) {
        this.id = id;
        this.ownerId = ownerId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.address = address;
        this.bartendersNeeded = bartendersNeeded;
        this.description = description;
        this.users = bartenders;
    }


    public Event() {
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public long getOwner_id() { return owner_id;}

    public void setOwner_id(long owner_id) { this.owner_id = owner_id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}
  
    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public long getBartendersNeeded() {return bartendersNeeded;}

    public void setBartendersNeeded(long bartendersNeeded) {this.bartendersNeeded = bartendersNeeded;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public List<User> getUsers() {return users;}

    public void setUsers(List<User> users) {this.users = users;}
}



