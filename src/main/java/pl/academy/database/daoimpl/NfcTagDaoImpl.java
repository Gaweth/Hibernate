package pl.academy.database.daoimpl;

import org.hibernate.Session;
import pl.academy.database.dao.NfcTagDao;
import pl.academy.database.entity.NfcTag;
import pl.academy.database.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

public class NfcTagDaoImpl implements NfcTagDao {

    public void save(NfcTag tag) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session.save(tag);
        session.getTransaction().commit();
        session.close();
    }

    public NfcTag findById(Long id) {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        NfcTag tag = session
                .createQuery("from NfcTag where id=:id", NfcTag.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .orElse(null);

        session.getTransaction().commit();
        session.close();

        return tag;
    }

    public List<NfcTag> findAll() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<NfcTag> list = session
                .createQuery("from NfcTag", NfcTag.class)
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
                .createQuery("delete NfcTag where id=:id")
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
