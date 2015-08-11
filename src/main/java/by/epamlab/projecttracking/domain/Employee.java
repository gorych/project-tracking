package by.epamlab.projecttracking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    private String m(){
        return "";
    }

    @Size(min = 2, max = 15,
            message = "Firstname must be between 2 and 15 characters long.")
    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @Size(min = 4, max = 20,
            message = "Lastname must be between 4 and 20 characters long.")
    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @Size(min = 4, max = 15,
            message = "Login must be between 4 and 15 characters long.")
    @NotNull
    @Column(name = "login")
    private String login;

    @Size(min = 4, max = 15,
            message = "Password must be between 4 and 15 characters long.")
    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "position_id")
    private Position position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", position=" + position +
                '}';
    }
}
