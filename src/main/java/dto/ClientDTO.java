package dto;

public class ClientDTO {

    private long id;

    private String name;

    private String phoneNumber;

    public ClientDTO() {
    }

    public ClientDTO(long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}' + '\n';
    }
}
