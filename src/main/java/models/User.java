package models;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="role_id", referencedColumnName = "role_id")
    private Role role;
    @Column(name="name", length = 100)
    private String name;

    @Column (name="password", length = 150, nullable = false)
    private String password;
    @Column (name="phone_number", length = 10, nullable = false)
    private String phoneNumber;
    @Column (name="date_of_birth")
    private Date BirthDate;
    @Column (name = "email")
    private String email;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(Role role, String name, String password, String phoneNumber, Date birthDate, String email) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        BirthDate = birthDate;
        this.email = email;
    }
}
