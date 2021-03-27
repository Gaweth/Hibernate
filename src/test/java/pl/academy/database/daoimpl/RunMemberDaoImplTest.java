package pl.academy.database.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.academy.database.dao.RunMemberDao;
import pl.academy.database.entity.Run;
import pl.academy.database.entity.RunMember;
import pl.academy.database.utils.HibernateUtils;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class RunMemberDaoImplTest {
    private RunMemberDao runMemberDao = new RunMemberDaoImpl();

    @BeforeEach
    void clearTable() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        session.createQuery("delete RunMember").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() throws SQLException {
        RunMember member = new RunMember();
        member.setName("Damian");

        runMemberDao.save(member);

        RunMember saved = runMemberDao.findById(member.getId());

        assertNotNull(saved);
    }

    @Test
    void findByNameFragment() throws SQLException {
        RunMember member1 = new RunMember();
        member1.setName("Damian");

        RunMember member2 = new RunMember();
        member2.setName("Woien");

        runMemberDao.save(member1);
        runMemberDao.save(member2);

        assertEquals(2, runMemberDao.findByNameFragment("%i_n").size());
        assertEquals(0, runMemberDao.findByNameFragment("olek").size());
    }
}
