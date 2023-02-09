package configuration;

import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Client.class);
            configuration.addAnnotatedClass(Driver.class);
            //configuration.addAnnotatedClass(Qualification.class);
            configuration.addAnnotatedClass(Transport.class);
            //configuration.addAnnotatedClass(TransportType.class);
            configuration.addAnnotatedClass(Vehicle.class);
            //configuration.addAnnotatedClass(VehicleType.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        return sessionFactory;
    }


}
