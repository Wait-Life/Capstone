package com.codeup.drinkhustle.Models;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import java.util.List;


@Entity
@Table(name = "Users")
public class User {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int isClient;

    @Column
    private String company;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, name = "tabc_cert")
    private String tabcCert;

    @Column(unique = true, name = "food_cert")
    private String foodCert;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Event> events;

    @Column
    private String photoUrl;


//    CONSTRUCTOR

    public User(long id, String name, String company, String email, String password, String tabc_cert, String food_cert, int isClient, String photoUrl) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.email = email;
        this.password = password;
        this.tabcCert = tabcCert;
        this.foodCert = foodCert;
        this.isClient = isClient;
        this.photoUrl = photoUrl;
    }

//    COPY CONSTRUCTOR
    public User(User copy) {
        id = copy.id;
        name = copy.name;
        company = copy.company;
        email = copy.email;
        password = copy.password;
        tabcCert = copy.tabcCert;
        foodCert = copy.foodCert;
        isClient = copy.isClient;
    }

    public User(long id, String name, String company, String email, String password) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.email = email;
        this.password = password;
    }


//    GETTERS AND SETTERS

    public User(){}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getIsClient() {
        return isClient;
    }

    public void setIsClient(int isClient) {
        this.isClient = isClient;
    }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getTabcCert() { return tabcCert; }

    public void setTabcCert(String tabc_cert) { this.tabcCert = tabc_cert; }

    public String getFoodCert() { return foodCert; }

    public void setFoodCert(String food_cert) {
        this.foodCert = food_cert;
    }

    public List<Event> getEvents() { return events; }

    public void setEvents(List<Event> events) { this.events = events; }
}
