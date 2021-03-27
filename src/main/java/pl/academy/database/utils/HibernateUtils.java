package pl.academy.database.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static HibernateUtils instance;

    private SessionFactory sessionFactory;

    public static HibernateUtils getInstance() {
        if(instance == null) {
            instance = new HibernateUtils();
        }
        return instance;
    }

    private HibernateUtils(){
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("Hibernate.xml")
                .build();
        Metadata metadata = new MetadataSources(standardRegistry)
                .getMetadataBuilder()
                .build();
         sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}

