package pl.academy.database.dao;

import pl.academy.database.entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {
    void save(Run run) throws SQLException;
    Run findById(Long id) throws SQLException;;
    List<Run> findALl() throws SQLException;;
    void deleteById(Long id) throws SQLException;
    List<Run> findRunsWithMembersLimitRange(int min, int max) throws SQLException;
    List<Run> findByNameFragment(String fragment) throws SQLException;
}
