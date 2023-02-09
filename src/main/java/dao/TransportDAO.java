package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class TransportDAO {

    public static void AddTransportToClient(long ClientID, Transport transport){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Client client = ClientDAO.getClient(ClientID);
            session.evict(client);
            session.refresh(client);

            transport.setClient(client);

            client.getTransports().add(transport);

            session.saveOrUpdate(transport);

            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void AssignTransportToDriver(long companyID, Transport transport){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            List<Driver> Drivers = CompanyDAO.getCompanyDrivers(companyID).stream().toList();

            session.evict(transport);
            session.refresh(transport);


            Random rand = new Random();
            int randomIndex = rand.nextInt(Drivers.size());
            Driver selectedDriver = Drivers.get(randomIndex);

            if(Drivers == null){
                return;
            }

            do {

                session.evict(selectedDriver);
                session.refresh(selectedDriver);
                if(selectedDriver.getQualification().toString() == transport.getTransportType().toString()){

                    if(selectedDriver.getTransports().contains(transport)){
                        randomIndex = rand.nextInt(Drivers.size());
                        selectedDriver = Drivers.get(randomIndex);
                        //continue;
                    } else {
                        selectedDriver.getTransports().add(transport);
                        transport.setDriver(selectedDriver);

                    }

                }

            }while(transport.getDriver() == null);

            session.saveOrUpdate(transport);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void RemoveTransportFromClient(long clientID, long TransportID){

        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();

            Client client = ClientDAO.getClient(clientID);
            Transport transport = TransportDAO.getTransport(TransportID);
            session.evict(transport);
            session.refresh(transport);
            session.evict(client);
            session.refresh(client);

            client.getTransports().remove(transport);
            transport.setClient(null);


            //vehicle = VehicleDAO.getVehicle();
            session.saveOrUpdate(transport);
            transaction.commit();
        }

    }

    public static void createTransport(String StartingPoint, String EndingPoint, LocalDate StartingDate,
                                       LocalDate EndingDate, TransportType transportType, BigDecimal price, Boolean isPaid, BigDecimal weight){

        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();

            Transport transport = new Transport(StartingPoint, EndingPoint, StartingDate, EndingDate, transportType, price, isPaid, weight);
            if(transport.getTransportType() == TransportType.PEOPLETRANSPORT){
                transport.setWeight(null);
            }

            session.save(transport);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveTransport(entity.Transport transport){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.save(transport);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveOrUpdateTransport(Transport transport){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.saveOrUpdate(transport);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void deleteTransport(Transport transport){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.delete(transport);
            transaction.commit();
        }
    }

    public static Transport getTransport(long id) {
        Transport transport;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            transport = session.get(Transport.class, id);

            transaction.commit();

        }

        return transport;
    }

}
