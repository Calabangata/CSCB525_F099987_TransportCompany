package entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name can not be blank!")
    @Size(min = 3, message = "The name must be at least 3 symbols")
    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "qualification", nullable = false)
    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    @PositiveOrZero
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @ManyToOne (fetch = FetchType.LAZY)
    private entity.Company company;

    @OneToMany(mappedBy = "driver")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "Driver")
    private List<Transport> Transports;

    public Driver() {
    }

    public Driver(String name) {
        this.name = name;
        //this.vehicles = new HashSet<>();
    }

    public Driver(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Driver(long id, String name, Qualification qualification) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
    }

    public Driver(String name, Qualification qualification, BigDecimal salary) {
        this.name = name;
        this.qualification = qualification;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Company getCompany() {
        return company;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Transport> getTransports() {
        return Transports;
    }

    public void setTransports(List<Transport> transports) {
        Transports = transports;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qualification=" + qualification +
                ", salary=" + salary +
                '}' + '\n';
    }
}
