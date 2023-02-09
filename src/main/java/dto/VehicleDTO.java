package dto;

import entity.VehicleType;

public class VehicleDTO {

    private long id;

    private String name;

    private VehicleType vehicleType;

    private String licensePlate;

    public VehicleDTO() {
    }

    public VehicleDTO(long id, String name, VehicleType vehicleType, String licensePlate) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vehicleType=" + vehicleType +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
