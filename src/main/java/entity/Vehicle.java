package entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The name of the vehicle must not be blank!")
    @Size(min = 2, message = "Invalid name!")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "vehicleType", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @NotBlank(message = "The license plate must not be blank!")
    @Size(min = 4, max = 10, message = "The license plate is invalid!")
    @Column(name = "licensePlate", nullable = false)
    private String licensePlate;

    @ManyToOne (fetch = FetchType.LAZY)
    private Driver driver;
    public Vehicle() {
    }

    public Vehicle(String name) {
        this.name = name;
    }

    public Vehicle(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Vehicle(long id, String name, VehicleType vehicleType, String licensePlate) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicleType=" + vehicleType +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
