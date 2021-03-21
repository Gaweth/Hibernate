package pl.academy.database.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernaeUtils {
    private static HibernaeUtils instance;

    private SessionFactory sessionFactory;

    public static HibernaeUtils getInstance() {
        if(instance == null) {
            instance = new HibernaeUtils();
        }
        return instance;
    }

    private HibernaeUtils(){
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

