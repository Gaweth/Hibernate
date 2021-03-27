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
    public void save(RunMember member) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        session.saveOrUpdate(member);

        session.getTransaction().commit();
        session.close();
    }

    public RunMember findById(Long id) {
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

        session.getTransaction().commit();
        session.close();

        return member;
    }

    public List<RunMember> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<RunMember> list = session
                .createQuery("from RunMember", RunMember.class)
                .list();

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
                .createQuery("delete RunMember where id=:id")
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public List<RunMember> findByNameFragment(String fragment) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<RunMember> list = session
                .createQuery("from RunMember where name like :name", RunMember.class)
                .setParameter("name", "%" + fragment + "%")
                .list();

        session.getTransaction().commit();
        session.close();

        return list;
    }
}
