package dao;

import entity.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ClientDAO {

    public static void createClient(String name, String phoneNumber){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            Client client = new Client();
            client.setName(name);
            client.setPhoneNumber(phoneNumber);

            session.save(client);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }


    public static void saveClient(entity.Client client){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.save(client);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void saveOrUpdateClient(Client client){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        }catch (javax.validation.ConstraintViolationException e){
            e.printStackTrace();
        }
    }

    public static void deleteClient(Client client){
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction =session.beginTransaction();
            session.delete(client);
            transaction.commit();
        }
    }

    public static Client getClient(long id) {
        Client client;
        try (Session session = configuration.SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            client = session.get(Client.class, id);

            transaction.commit();

        }

        return client;
    }

}
