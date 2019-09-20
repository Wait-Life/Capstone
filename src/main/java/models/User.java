package models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Users")
public class User {

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Event> events;

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isClient;

    @Column
    private String company;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;

    @Column(unique = true)
    private String tabc_cert;

    @Column(unique = true)
    private String food_cert;



//    CONSTRUCTOR
    public User(long id, String name, String company, String email, String password, String tabc_cert, String food_cert) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.email = email;
        this.password = password;
        this.tabc_cert = tabc_cert;
        this.food_cert = food_cert;
    }

//    COPY CONSTRUCTOR
    public User(User copy) {
        id = copy.id;
        name = copy.name;
        company = copy.company;
        email = copy.email;
        password = copy.password;
        tabc_cert = copy.tabc_cert;
        food_cert = copy.food_cert;
    }


//    GETTERS AND SETTERS

    public User(){}

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isClient(){
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public String getCompany() { return company; }

    public void setCompany(String company) { this.company = company; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getTabc_cert() { return tabc_cert; }

    public void setTabc_cert(String tabc_cert) { this.tabc_cert = tabc_cert; }

    public String getFood_cert() { return food_cert; }

    public void setFood_cert(String food_cert) {
        this.food_cert = food_cert;
    }

    public List<Event> getEvents() { return events; }

    public void setEvents(List<Event> events) { this.events = events; }
}
