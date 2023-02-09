package dao;

import configuration.SessionFactoryUtil;
import entity.Company;
import entity.Driver;
import entity.Qualification;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Set;

public class DriverDAO {

        public static void AddDriverToCompany(long CompanyID, Driver driver, BigDecimal salary){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Company company = CompanyDAO.getCompany(CompanyID);
            session.evict(company);
            session.refresh(company);

//            session.evict(driver);
//            session.refresh(driver);

            driver.setCompany(company);
            driver.setSalary(salary);

            company.getDrivers().add(driver);

            session.saveOrUpdate(driver);

            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void RemoveDriverFromCompany(long companyID, long DriverID){

        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Company company = CompanyDAO.getCompany(companyID);
            Driver driver = DriverDAO.getDriver(DriverID);
            session.evict(company);
            session.refresh(company);
            session.evict(driver);
            session.refresh(driver);


            company.getDrivers().remove(driver);
            driver.setCompany(null);

            session.saveOrUpdate(driver);
            transaction.commit();
        }

    }

    public static Set<Vehicle> getVehiclesOfDriver(long id){
        Driver driver;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver = session.createQuery("select c from Driver c" +
                    " join fetch c.vehicles" + " where c.id = :id", Driver.class)
                            .setParameter("id", id).getSingleResult();

            transaction.commit();
        }
        return driver.getVehicles();
    }

    public static void createDriver(String name, Qualification qualification){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();

            Driver driver = new Driver(name);
            driver.setQualification(qualification);

            session.save(driver);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveDriver(entity.Driver driver){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.save(driver);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveOrUpdateDriver(Driver driver){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.saveOrUpdate(driver);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void deleteDriver(Driver driver){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.delete(driver);
            transaction.commit();
        }
    }

    public static Driver getDriver(long id) {
        Driver driver;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            driver = session.get(Driver.class, id);

            transaction.commit();

        }

        return driver;
    }

}
