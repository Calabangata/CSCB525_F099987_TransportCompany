package dto;

import java.math.BigDecimal;

public class CompanyDTO {

    private long id;

    private String name;

    private String address;

    private BigDecimal income;

    public CompanyDTO() {
    }

    public CompanyDTO(long id, String name, String address, BigDecimal income) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.income = income;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", income=" + income +
                '}';
    }
}
