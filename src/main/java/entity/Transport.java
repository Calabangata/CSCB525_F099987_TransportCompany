package entity;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;



@Entity
@Table(name = "transport")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "The starting point must not be blank!")
    @Column(name="startingPoint", nullable = false)
    private String startingPoint;

    @NotBlank(message = "The ending point must not be blank!")
    @Column(name="endingPoint", nullable = false)
    private String endingPoint;

    @FutureOrPresent
    @Column(name="startingDate", nullable = false)
    private LocalDate startingDate;

    @FutureOrPresent
    @Column(name="endingDate", nullable = false)
    private LocalDate endingDate;

    @Column(name="transportType", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportType transportType;

    @Positive
    @Column(name="price", nullable = false)
    private BigDecimal price;

    @Column(name = "isPaid", nullable = false)
    private Boolean isPaid;

    @PositiveOrZero
    @Column(name = "weight")
    private BigDecimal weight;

    @ManyToOne (fetch = FetchType.LAZY)
    private entity.Client client;

    @ManyToOne (fetch = FetchType.LAZY)
    private entity.Driver Driver;

    public Transport() {
    }

    public Transport(String startingPoint, String endingPoint, LocalDate startingDate, LocalDate endingDate, BigDecimal price, TransportType transportType, Boolean ispaid) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.price = price;
        this.transportType = transportType;
        this.isPaid = ispaid;
    }

    public Transport(long id, String startingPoint, String endingPoint, LocalDate startingDate, LocalDate endingDate, BigDecimal price, TransportType transportType, Boolean isPaid) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.price = price;
        this.transportType = transportType;
        this.isPaid = isPaid;
    }

    public Transport(String startingPoint, String endingPoint, LocalDate startingDate, LocalDate endingDate, TransportType transportType, BigDecimal price, Boolean isPaid, BigDecimal weight) {

        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.transportType = transportType;
        this.price = price;
        this.isPaid = isPaid;
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public entity.Driver getDriver() {
        return Driver;
    }

    public void setDriver(entity.Driver driver) {
        Driver = driver;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", endingPoint='" + endingPoint + '\'' +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", transportType=" + transportType +
                ", price=" + price +
                ", isPaid=" + isPaid +
                '}';
    }
}
