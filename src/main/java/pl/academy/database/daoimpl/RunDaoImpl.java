package pl.academy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.academy.database.dao.RunDao;
import pl.academy.database.entity.Run;
import pl.academy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RunDaoImpl implements RunDao {
    public void save(Run run) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session.save(run);
        session.getTransaction().commit();
        session.close();
    }

    public Run findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Optional<Run> run = session
                .createQuery("from Run where id=:id", Run.class)
                .setParameter("id", id)
                .uniqueResultOptional();

        session.getTransaction().commit();
        session.close();

        return run.orElse(null);
    }

    public List<Run> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<Run> list = session.createQuery("from Run", Run.class).list();

        session.getTransaction().commit();
        session.close();

        return list;
    }

    public void deleteById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session
                .createQuery("delete Run where id=:id")
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public List<Run> findRunsWithMembersLimitRange(int min, int max) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<Run> list = session
                .createQuery("from Run where membersLimit > :min and membersLimit < :max", Run.class)
                .setParameter("max", max)
                .setParameter("min", min)
                .list();

        session.getTransaction().commit();
        session.close();

        return list;
    }
}
