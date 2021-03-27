package pl.academy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.academy.database.dao.RunMemberDao;
import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;
import pl.academy.database.utils.HibernateUtils;

import javax.persistence.NoResultException;
import java.lang.reflect.Member;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

public class RunMemberDaoImpl implements RunMemberDao {
    public void save(RunMember member) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session.saveOrUpdate(member);
        session.getTransaction().commit();
        session.close();

    }

    public RunMember findById(Long id) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        RunMember member = session
                .createQuery("from RunMember where id=:id", RunMember.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse(null);
        session.getTransaction()
                .commit();
        session.close();

        return member;
    }

    public List<RunMember> findALl() throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<RunMember> runList = session
                .createQuery("from RunMember", RunMember.class)
                .list();

        session.getTransaction().commit();
        session.close();
        return runList;
    }

    public void deleteById(Long id) throws SQLException {
        SessionFactory sessionFactory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        RunMember run = null;
        run = session
                .createQuery("from Run where id=:id", RunMember.class)
                .setParameter("id", id)
                .getSingleResult();
        session.delete(run);
        session.getTransaction().commit();
        session.close();
    }

    public List<RunMember> findByNameFragment(String fragment) throws SQLDataException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        List<RunMember> runList = session
                .createQuery("from Run where name like :name",RunMember.class)
                .setParameter("name", "%" + fragment + "%")
                .list();
        session.getTransaction().commit();
        session.close();

        return runList;
    }
}
