package pl.academy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.academy.database.entity.Run;
import pl.academy.database.utils.HibernateUtils;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RunDaoImplTest {

    RunDaoImpl runDao = new RunDaoImpl();

    @BeforeEach
    private void clearTable() {
        SessionFactory factory = HibernateUtils
                .getInstance()
                .getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session
                .createQuery("delete Run")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        Run run = new Run(null, "Nowy bieg testowy", 100, 5);
        runDao.save(run);

        Run saved = runDao.findById(run.getId());
        Run notSaved = runDao.findById(888l);

        assertNotNull(saved);
        assertNull(notSaved);
    }

    @Test
    void findAll() {
        Run run1 = new Run(null, "Nowy bieg testowy", 100, 5);
        Run run2 = new Run(null, "Nowy bieg testowy", 100, 5);

        runDao.save(run1);
        runDao.save(run2);

        List<Run> list = runDao.findAll();
        assertNotNull(list);
        assertEquals(2, list.size());

        Run tested = null;
        if(list.get(0).getId().equals(run1.getId())) {
            tested = list.get(0);
        } else if (list.get(1).getId().equals(run1.getId())) {
            tested = list.get(1);
        }

        assertNotNull(tested);
    }

    @Test
    void deleteById() {
        Run run = new Run(null, "Nowy bieg testowy", 100, 5);
        runDao.save(run);

        assertNotNull(run.getId());

        runDao.deleteById(run.getId());
        Run deleted = runDao.findById(run.getId());

        assertNull(deleted);
    }

    @Test
    void findRunsWithMembersLimitRange() {
        Run run1 = new Run(null, "Nowy bieg testowy", 50, 5);
        Run run2 = new Run(null, "Nowy bieg testowy", 100, 5);
        Run run3 = new Run(null, "Nowy bieg testowy", 150, 5);

        runDao.save(run1);
        runDao.save(run2);
        runDao.save(run3);

        assertEquals(1, runDao.findRunsWithMembersLimitRange(90, 110).size());
        assertEquals(3, runDao.findRunsWithMembersLimitRange(49, 160).size());
        assertEquals(0, runDao.findRunsWithMembersLimitRange(160, 200).size());
    }
}
