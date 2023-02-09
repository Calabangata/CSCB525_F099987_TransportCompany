package entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The client s name can not be blank!")
    @Size(min = 3, max = 60, message = "The name must be between 3 and 6 characters!")
    @Column(name="name", nullable = false)
    private String name;

    @NotBlank(message = "The phone number must not be empty")
    @Digits(integer = 25, fraction = 0, message = "Invalid phone number format!")
    @Size(min = 10, max = 25, message = "Invalid phone number")
    //@Pattern(regexp = "^(0|\\+359)[0-9]+", message = "The phone number must start with 0 or +359")
    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    private List<Transport> transports;

    @ManyToMany
    private Set<entity.Company> companies;

    public Client() {

    }

    public Client(long id, String name) {
        this.id = id;
        this.name = name;

    }

    public Client(String name) {
        this.name = name;
    }

    public Client(long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
