package dao;

import entity.Driver;
import entity.Vehicle;
import entity.VehicleType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.validation.ConstraintViolationException;


public class VehicleDAO {


    public static void AddDriverToVehicle(long driverID, Vehicle vehicle){

        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Driver driver = DriverDAO.getDriver(driverID);
            session.evict(driver);
            session.refresh(driver);
            vehicle.setDriver(driver);
            driver.getVehicles().add(vehicle);
            //vehicle = VehicleDAO.getVehicle();
            session.saveOrUpdate(vehicle);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }

    }

    public static void RemoveVehicleFromDriver(long driverID, long vehicleID){

        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Driver driver = DriverDAO.getDriver(driverID);
            Vehicle vehicle = VehicleDAO.getVehicle(vehicleID);
            session.evict(vehicle);
            session.refresh(vehicle);
            session.evict(driver);
            session.refresh(driver);

            driver.getVehicles().remove(vehicle);
            vehicle.setDriver(null);


            //vehicle = VehicleDAO.getVehicle();
            session.saveOrUpdate(vehicle);
            transaction.commit();
        }

    }

    public static void createVehicle(String name, VehicleType type, String plate){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();

            Vehicle vehicle = new Vehicle(name);
            vehicle.setVehicleType(type);
            vehicle.setLicensePlate(plate);
            session.save(vehicle);

            transaction.commit();
        }catch (ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveVehicle(entity.Vehicle vehicle){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.save(vehicle);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveOrUpdateVehicle(Vehicle vehicle){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.saveOrUpdate(vehicle);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void deleteVehicle(Vehicle vehicle){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.delete(vehicle);
            transaction.commit();
        }
    }

    public static Vehicle getVehicle(long id) {
        Vehicle vehicle;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle = session.get(Vehicle.class, id);

            transaction.commit();

        }

        return vehicle;
    }

}
