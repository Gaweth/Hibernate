package pl.academy.database.utils.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.academy.database.dao.NfcTagDao;
import pl.academy.database.dao.RunDao;
import pl.academy.database.dao.RunMemberDao;
import pl.academy.database.daoimpl.NfcTagDaoImpl;
import pl.academy.database.daoimpl.RunDaoImpl;
import pl.academy.database.daoimpl.RunMemberDaoImpl;
import pl.academy.database.entity.NfcTag;
import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;
import pl.academy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


        //oneToManyTest();
        oneTomanySelectTest();

        HibernateUtils
                .getInstance()
                .getSessionFactory()
                .close();
    }

    private static void manyToManySaveTest() throws SQLException {
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        NfcTagDao nfcTagDao = new NfcTagDaoImpl();

        RunMember member1 = new RunMember();
        member1.setName("Adam");
        RunMember member2 = new RunMember();
        member2.setName("wojciech");

        runMemberDao.save(member1);
        runMemberDao.save(member2);

        NfcTag tag1 = new NfcTag();

        tag1.setSerialNumber("tag number 1");
        tag1.getMembers().add(member1);
        tag1.getMembers().add(member2);
        nfcTagDao.save(tag1);

        NfcTag tag2= new NfcTag();
        tag2.setSerialNumber("tag number 2");
        tag2.getMembers().add(member1);
        tag2.getMembers().add(member2);
        nfcTagDao.save(tag2);



    }


    private static void oneToManyTest() throws SQLException {
        RunDao runDao = new RunDaoImpl();
        RunMemberDao runMemberDao = new RunMemberDaoImpl();

        Run run = new Run();
        run.setName("bieg na 10 ");

        runDao.save(run);

        for (int i = 0; i < 10; i++) {

            RunMember member = new RunMember();
            member.setName("biegacz do biegu z wieloma" + i);
            runMemberDao.save(member);

            member.setRun(run);
            runMemberDao.save(member);
        }

    }

    static void oneTomanySelectTest() throws SQLException {
        RunDao runDao = new RunDaoImpl();

        Run run = runDao.findById(117l);

        System.out.printf("bieg" + run.getName());
        System.out.printf("ilosc uczestnikow = " + run.getMembers().size());


    }

    public static void insertOneRun() {
        Run run = new Run();
        run.setName("Rzeszowska piątka");
        run.setMembersLimit(1000);

        SessionFactory sessionFactory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();
    }

    private static void selectOneRun() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        Run run = null;
        try {
            run = session
                    .createQuery("from Run where id=:id", Run.class)
                    .setParameter("id", 17l)
                    .getSingleResult();
        }catch (NoResultException e) {}

        session.getTransaction().commit();
        session.close();
        if(run != null) {
            System.out.println("Bieg: " + run.getName());
        } else {
            System.out.println("Brak takiego biegu");
        }
    }

    private static void printAllRuns(){
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Run> list = session
                .createQuery("from Run", Run.class)
                .list();
        session.getTransaction().commit();
        session.close();
        for (Run run: list) {
            System.out.printf("id=%d name=%s limit=%d \n",
                    run.getId(),
                    run.getName(),
                    run.getMembersLimit());
        }

    }
}
