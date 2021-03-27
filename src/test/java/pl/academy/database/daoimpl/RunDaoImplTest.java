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
    private RunDaoImpl runDao = new RunDaoImpl();

    @BeforeEach
    private void deleteAll() throws SQLException {
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
    void save() throws SQLException {
        Run run = new Run(null, "Testowy bieg", 99);
        try {
            runDao.save(run);
            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());
        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void findById() throws SQLException {


    }


    @Test
    void findAll() throws SQLException {
        try {
            Run run1 = new Run(null, "Bieg numer 100", 99);
            Run run2 = new Run(null, "Inny bieg", 20);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> runList = runDao.findALl();

            assertNotNull(runList);
            assertEquals(2, runList.size());

            Run testRun1 = null;
            if (runList.get(0).getId() == run1.getId()) {
                testRun1 = runList.get(0);
            } else if (runList.get(1).getId() == run1.getId()) {
                testRun1 = runList.get(1);
            }

            assertNotNull(testRun1);
            assertEquals(run1.getId(), testRun1.getId());
            assertEquals(run1.getName(), testRun1.getName());
            assertEquals(run1.getMembersLimit(), testRun1.getMembersLimit());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void deleteById() throws SQLException {
        Run run = new Run(null, "Bieg do usuniecia", 100);
        try {
            runDao.save(run);
            runDao.deleteById(run.getId());
            Run deleted = runDao.findById(run.getId());

            assertNull(deleted);
        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void findRunsWithMembersLimitRange() {
        try {
            Run run1 = new Run(null, "bieg", 50);
            Run run2 = new Run(null, "zbieg2", 100);
            Run run3 = new Run(null, "wielki bieg", 150);

            runDao.save(run1);
            runDao.save(run2);
            runDao.save(run3);

            List<Run> found1 = runDao.findRunsWithMembersLimitRange(40, 80);
            assertNotNull(found1);
            assertEquals(1, found1.size());
            assertEquals(run1.getId(), found1.get(0).getId());
            assertEquals(run1.getName(), found1.get(0).getName());
            assertEquals(run1.getMembersLimit(), found1.get(0).getMembersLimit());

            List<Run> found2 = runDao.findRunsWithMembersLimitRange(40, 200);
            assertEquals(3, found2.size());

            List<Run> found3 = runDao.findRunsWithMembersLimitRange(156, 200);
            assertEquals(0, found3.size());


        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void findByNameFragment() {

        try {
            Run run1 = new Run(null, "maly bieg", 50);
            Run run2 = new Run(null, "zbieg2", 50);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> found1 = runDao.findByNameFragment("maly");
            assertNotNull(found1);
            assertEquals(1, found1.size());
            assertEquals(run1.getId(), found1.get(0).getId());
            assertEquals(run1.getName(), found1.get(0).getName());
            assertEquals(run1.getMembersLimit(), found1.get(0).getMembersLimit());

            List<Run> found2 = runDao.findByNameFragment("ieg");
            assertNotNull(found2);
            assertEquals(2, found2.size());

            List<Run> found3 = runDao.findByNameFragment("fhgdh");
            assertNotNull(found3);
            assertEquals(0, found3.size());

        } catch (SQLException e) {
            fail(e);
        }
    }
}
