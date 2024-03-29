package com.codeup.drinkhustle.Models;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Text;

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

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "phone_num")
    private String phoneNum;

    @Column(name = "about_me", columnDefinition = "TEXT")
    private String aboutMe;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;



//    CONSTRUCTOR

    public User(long id, String name, String company, String email, String password, String tabcCert, String foodCert, int isClient, String photoUrl, String phoneNum, List <Event> events, String aboutMe) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.email = email;
        this.password = password;
        this.tabcCert = tabcCert;
        this.foodCert = foodCert;
        this.isClient = isClient;
        this.photoUrl = photoUrl;
        this.events = events;
        this.phoneNum = phoneNum;
        this.aboutMe = aboutMe;
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
        photoUrl = copy.photoUrl;
        events = copy.events;
        phoneNum = copy.phoneNum;
        aboutMe = copy.aboutMe;
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

    public void setTabcCert(String tabcCert) { this.tabcCert = tabcCert; }

    public String getFoodCert() { return foodCert; }

    public void setFoodCert(String foodCert) {
        this.foodCert = foodCert;
    }

    public String getPhotoUrl() { return photoUrl; }

    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public List<Event> getEvents() { return events; }

    public void setEvents(List<Event> events) { this.events = events; }

    public String getPhoneNum() { return phoneNum; }

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getAboutMe() { return aboutMe; }

    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }
}
