package dto;

import entity.Qualification;

import java.math.BigDecimal;

public class DriverDTO {

    private long id;

    private String name;

    private Qualification qualification;

    private BigDecimal salary;

    public DriverDTO() {
    }

    public DriverDTO(long id, String name, Qualification qualification, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qualification=" + qualification +
                ", salary=" + salary +
                '}' + '\n';
    }
}
