package pl.academy.database.utils.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;
import pl.academy.database.utils.HibernaeUtils;

import javax.persistence.NoResultException;

public class Main {
    public static void main(String[] args) {


       selectOnerun();



        HibernaeUtils
                .getInstance()
                .getSessionFactory()
                .close();

    }

    private static void insertOneRun(){
        Run run = new Run();
        run.setName("pieg pierwszy");
        run.setMembersLimit(1005);

        SessionFactory sessionFactory = HibernaeUtils
                .getInstance()
                .getSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();

    }

    private static void selectOnerun(){
        SessionFactory factory = HibernaeUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Run run = null;
        try {
           run = session
                    .createQuery("from Run where id=.id", Run.class)
                    .setParameter("id", 1l)
                    .getSingleResult();
        } catch (NoResultException e) { }

        session.getTransaction().commit();
        session.close();
//        if (run != null){
//            System.out.println("bieg " + run.getId() + " nieistnieje" );
//        }
//        System.out.println("bieg" + run.getName());
   }
}
