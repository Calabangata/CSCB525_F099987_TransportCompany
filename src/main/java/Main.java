import dao.*;
import entity.*;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.w3c.dom.stylesheets.LinkStyle;


import java.math.BigDecimal;
import java.sql.SQLDataException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

    public static void main(String args[]){


        DriverDAO.createDriver("", Qualification.PEOPLETRANSPORT);
        //ClientDAO.createClient("", "085446768");
        // VehicleDAO.createVehicle("Iveco", VehicleType.AUTOBUS, "CA5486BR");
        //CompanyDAO.createCompany("", "akddvfkvfb");

//        Client client = ClientDAO.getClient(1);
//        Client client2 = ClientDAO.getClient(3);
//        Client client3 = ClientDAO.getClient(5);
//
//        CompanyDAO.AddClientToCompany(1, client);
//        CompanyDAO.AddClientToCompany(1, client2);
//        CompanyDAO.AddClientToCompany(1, client3);

//        Driver driver = DriverDAO.getDriver(9);
//        DriverDAO.AddDriverToCompany(1, driver3, BigDecimal.valueOf(2000));

//        CompanyDAO.WriteFile("test.txt", 1);
//
//        CompanyDAO.ReadFromFile("test.txt");

    }

}
