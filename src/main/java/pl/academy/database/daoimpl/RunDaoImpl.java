package pl.academy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.academy.database.dao.RunDao;
import pl.academy.database.entity.Run;
import pl.academy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class RunDaoImpl implements RunDao {


    public void save(Run run) throws  SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();

        }

    public Run findById(Long id) throws  SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        Run run = null;
        try {
            run = session
                    .createQuery("from Run where id=:id", Run.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        session.getTransaction().commit();
        session.close();
        return run;
    }


    public List<Run> findALl() throws  SQLException {
        SessionFactory sessionFactory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Run> runList = session
                .createQuery("from Run", Run.class)
                .list();

        session.getTransaction().commit();
        session.close();
        return runList;
    }


    public void deleteById(Long id) throws  SQLException {
        SessionFactory sessionFactory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Run run = null;
        run = session
                .createQuery("from Run where id=:id", Run.class)
                .setParameter("id", id)
                .getSingleResult();
        session.delete(run);
        session.getTransaction().commit();
        session.close();
    }

    public List<Run> findRunsWithMembersLimitRange(int min, int max) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        List<Run> runList = session
                .createQuery("from Run where membersLimit > :min and membersLimit < :max", Run.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

        session.getTransaction().commit();
        session.close();


        return runList;
    }

    public List<Run> findByNameFragment(String fragment) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        List<Run> runList = session
                .createQuery("from Run where name like :name",Run.class)
                .setParameter("name", "%" + fragment + "%")
                .list();
        session.getTransaction().commit();
        session.close();

        return runList;

    }

}

