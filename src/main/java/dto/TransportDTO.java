package dto;

import entity.TransportType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransportDTO {

    private long id;

    private String startingPoint;

    private String endingPoint;

    private LocalDate startingDate;

    private LocalDate endingDate;

    private TransportType transportType;

    private BigDecimal price;

    private Boolean isPaid;

    public TransportDTO() {
    }

    public TransportDTO(long id, String startingPoint, String endingPoint, LocalDate startingDate, LocalDate endingDate, TransportType transportType, BigDecimal price, Boolean isPaid) {
        this.id = id;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.transportType = transportType;
        this.price = price;
        this.isPaid = isPaid;
    }

    @Override
    public String toString() {
        return "TransportDTO{" +
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
