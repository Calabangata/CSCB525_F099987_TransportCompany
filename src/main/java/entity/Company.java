package entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The company's name must not be blank!")
    @Size(min = 3, message = "The company's name must be at least 3 characters long!")
    @Column(name="name", nullable = false)
    private String name;

    @NotBlank(message = "The company's address must not be blank!")
    @Column(name="address", nullable = false)
    private String address;

    @Positive
    @Column(name="income")
    private BigDecimal income;

    //@ManyToOne
    @OneToMany(mappedBy = "company")
    private Set<Driver> drivers;

    @ManyToMany(mappedBy = "companies")
    private Set<Client> clients;

    public Company() {

    }

    public Company(String name) {
        this.name = name;
    }

    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company(String name, String address, BigDecimal income) {
        this.name = name;
        this.address = address;
        this.income = income;
    }

    public Company(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public Set<Client> getClients() {
        return clients;
    }



    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", income=" + income +
                '}';
    }
}
