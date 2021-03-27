package pl.academy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.academy.database.dao.NfcTagDao;
import pl.academy.database.entity.NfcTag;
import pl.academy.database.utils.HibernateUtils;

import static org.junit.jupiter.api.Assertions.*;

class NfcTagDaoImplTest {

    private NfcTagDao tagDao = new NfcTagDaoImpl();





    @BeforeEach
    private void clearTable() {

        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete NfcTag ")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }


    @Test
    void save() {
        NfcTag tag = new NfcTag();
        tag.setSerialNumber("serial serial serial");

        tagDao.save(tag);

        NfcTag saved = tagDao.findById(tag.getId());

        assertNotNull(saved);
    }


    @Test
    void findById() {
    }

    @Test
    void findALl() {
    }

    @Test
    void deleteById() {
    }
}